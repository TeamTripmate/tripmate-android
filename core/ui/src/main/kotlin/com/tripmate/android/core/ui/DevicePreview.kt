package com.tripmate.android.core.ui

import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Portrait",
    showBackground = true,
    device = "spec:width=360dp,height=800dp,dpi=411",
)
annotation class DevicePreview

/*

@DevicePreview
@Composable
fun WaitingScreenPreview() {
    TripmateTheme {
        WaitingScreen(padding = PaddingValues())
    }
}
와 같이 컴포즈로 구현된 화면을 디바이스 사이즈로 미리보기 할 수 있게 해주는 어노테이션입니다.

 */
