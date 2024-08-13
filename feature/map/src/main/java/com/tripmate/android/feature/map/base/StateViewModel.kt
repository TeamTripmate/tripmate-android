package io.hlab.sample.app.presentation.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow

abstract class StateViewModel<S : ViewModelState>(
    initialState: S,
) : ViewModel() {
    private val tag by lazy { javaClass.simpleName }

    private val stateStore = StateStore(initialState = initialState, scope = viewModelScope)

    private val state: S get() = stateStore.state
    private val stateFlow: Flow<S> get() = stateStore.flow

    protected fun setState(reducer: S.() -> S) {
        stateStore.set(reducer)
    }

    protected fun withState(action: (state: S) -> Unit) {
        stateStore.get(action)
    }

    @Composable
    open fun collectAsState(): State<S> {
        return stateFlow.collectAsState(initial = state)
    }
}