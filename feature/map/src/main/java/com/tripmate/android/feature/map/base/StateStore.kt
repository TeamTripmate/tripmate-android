package io.hlab.sample.app.presentation.base

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.selects.select
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class StateStore<S : ViewModelState>(
    initialState: S,
    scope: CoroutineScope,
    private val contextOverride: CoroutineContext = EmptyCoroutineContext,
) {
    private val setStateChannel = Channel<S.() -> S>(capacity = Channel.UNLIMITED)
    private val withStateChannel = Channel<(S) -> Unit>(capacity = Channel.UNLIMITED)

    private val stateSharedFlow = MutableSharedFlow<S>(
        replay = 1,
        extraBufferCapacity = SubscriberBufferSize,
        onBufferOverflow = BufferOverflow.SUSPEND,
    ).apply { tryEmit(initialState) }

    @Volatile
    var state = initialState
    val flow: Flow<S> = stateSharedFlow.asSharedFlow()

    init {
        flushQueues(scope = scope)
    }

    fun get(block: (S) -> Unit) {
        withStateChannel.trySend(block)
    }

    fun set(stateReducer: S.() -> S) {
        setStateChannel.trySend(stateReducer)
    }

    private fun flushQueues(scope: CoroutineScope) {
        scope.launch(flushDispatcher + contextOverride) {
            while (isActive) {
                flushQueuesOnce()
            }
        }
    }

    private suspend fun flushQueuesOnce() {
        select<Unit> {
            setStateChannel.onReceive { reducer ->
                val newState = state.reducer()
                if (newState != state) {
                    state = newState
                    stateSharedFlow.emit(newState)
                }
            }
            withStateChannel.onReceive { block ->
                block(state)
            }
        }
    }

    companion object {
        private val flushDispatcher = Executors.newCachedThreadPool().asCoroutineDispatcher()

        // 2^6 - 1
        private const val SubscriberBufferSize = 63
    }
}