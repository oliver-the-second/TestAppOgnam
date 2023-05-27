package com.ilhomsoliev.testappognam.features.login.presentation.code

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Top
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight.Companion.SemiBold
import androidx.compose.ui.text.input.KeyboardType.Companion.NumberPassword
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.ilhomsoliev.testappognam.R

data class CodeState(
    val code: String,
    val focuses: List<FocusRequester>,
    val sec: Int,
)

interface CodeCallback {

    fun onCodeChange(index: Int, text: String)
    fun onCodeSend()
    fun onBack()
}

@Composable
fun CodeContent(
    state: CodeState,
    modifier: Modifier = Modifier,
    callback: CodeCallback? = null,
) {
    Box(
        modifier.background(
            colorScheme.background
        )
    ) {
        Column(
            Modifier.fillMaxSize(),
            Top, CenterHorizontally
        ) {
            Column(modifier.fillMaxWidth()) {
                IconButton({ callback?.onBack() }, Modifier.padding(top = 16.dp)) {
                    Icon(
                        painterResource(
                            com.ilhomsoliev.testappognam.R.drawable.ic_back
                        ),
                        "Номер телефона",
                        Modifier.size(24.dp),
                        colorScheme.tertiary
                    )
                }
                Row(
                    Modifier.padding(start = 16.dp),
                    verticalAlignment = Alignment.Bottom
                ) {
                    Text(
                        "Код",
                        Modifier,
                        style = typography.titleLarge,
                        color = colorScheme.tertiary
                    )
                }
            }
            DigitCode(
                Modifier.padding(5.dp),
                state.code, state.focuses
            ) { index, it ->
                callback?.onCodeChange(index, it)
            }
            ButtonTimer(
                Modifier.padding(top = 20.dp),
                state.sec
            ) { callback?.onCodeSend() }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DigitCode(
    modifier: Modifier = Modifier,
    code: String,
    focuses: List<FocusRequester>,
    onChange: (Int, String) -> Unit,
) {
    Row {
        focuses.forEachIndexed { index, focus ->
            TextField(
                if (code.length > index)
                    code[index].toString() else "",
                { onChange(index, it) }, modifier
                    .clip(shapes.large)
                    .size(60.dp)
                    .focusRequester(focus),
                keyboardOptions = KeyboardOptions(
                    keyboardType = NumberPassword
                ),
                singleLine = true
            )
        }
    }
}

@Composable
private fun ButtonTimer(
    modifier: Modifier = Modifier,
    sec: Int,
    onResend: () -> Unit,
) {
    Box(
        modifier
            .fillMaxWidth()
            .clip(CircleShape)
            .clickable { if (sec <= 0) onResend() },
        Center
    ) {
        val style = typography.bodyMedium.copy(
            color = colorScheme.onTertiary,
            fontWeight = SemiBold,
            textAlign = TextAlign.Center
        )
        if (sec > 0) Text(
            buildAnnotatedString {
                append("Вы можете повторно отправить код в")
                withStyle(
                    style.copy(Color.Black).toSpanStyle()
                ) { append(" $sec сек") }
            },
            Modifier.padding(6.dp),
            style = style
        ) else Button(modifier = Modifier.padding(horizontal = 16.dp),
            onClick = {
                onResend()
            }) {
            Text(text = "Повторить код", fontFamily = FontFamily(
                Font(R.font.roboto_regular)
            )
            )
        }
    }
}