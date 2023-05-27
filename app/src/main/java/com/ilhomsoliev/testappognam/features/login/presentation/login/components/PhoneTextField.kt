package com.ilhomsoliev.testappognam.features.login.presentation.login.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.KeyboardType.Companion.NumberPassword
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.ilhomsoliev.testappognam.R
import com.ilhomsoliev.testappognam.features.login.presentation.login.LoginCallback
import com.ilhomsoliev.testappognam.shared.common.transform.transformationOf
import com.ilhomsoliev.testappognam.shared.country.Country


@Composable
fun PhoneField(
    modifier: Modifier = Modifier,
    phone: String,
    country: Country,
    callback: LoginCallback? = null
) {
    Box(
        modifier.clip(MaterialTheme.shapes.extraSmall)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .background(colorScheme.onPrimaryContainer),
            Arrangement.Start, Alignment.CenterVertically
        ) {
            Image(
                country.flag,
                "Выберите страну",
                Modifier
                    .padding(start = 10.dp)
                    .size(20.dp)
                    .clickable
                    { callback?.changeCountry() }
            )
            PhoneTextField(
                phone, country,
                Modifier.fillMaxWidth(),
                { callback?.onClear() },
                { callback?.onPhoneChange(it) }
            )
        }
    }
}

@Composable
fun PhoneTextField(
    value: String,
    country: Country,
    modifier: Modifier = Modifier,
    onClear: () -> Unit,
    onValueChanged: (String) -> Unit,
) {
    val focusManager =
        LocalFocusManager.current
    var focused by remember {
        mutableStateOf(false)
    }
    
    val dial = country.phoneDial
    
    val mask = remember(country.phoneMask, dial) {
        country.phoneMask.replace(
            dial, dial.replace(
                Regex("\\d"), "#"
            )
        )
    }
    val length = remember(mask) {
        mask.count { it == '#' }
    }
    val transform = remember(mask) {
        transformationOf(mask)
    }
    
    TTextField(
        value = value,
        onValueChange = { text ->
            if(
                text.length <= country.clearPhoneDial.length
                || text.substring(
                    0, country.clearPhoneDial.length
                ) != country.clearPhoneDial
            ) onClear()
            else if(text.length <= length)
                onValueChanged(text)
        },
        modifier = modifier.onFocusChanged {
            focused = it.isFocused
        },
        shape = MaterialTheme.shapes.large,
        label = if(value.isNotEmpty())
            textFieldLabel(
                true, "Номер телефона"
            ) else null,
        placeholder = textFieldLabel(
            false, "Номер телефона"
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = NumberPassword
        ),
        keyboardActions = KeyboardActions {
            focusManager.clearFocus()
        },
        visualTransformation = transform,
        clear = if(
            focused && value.length != country
                .clearPhoneDial.length
        ) onClear else null,
        singleLine = true,
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = typography.bodyMedium,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(
        imeAction = ImeAction.Done, keyboardType = KeyboardType.Text,
        capitalization = KeyboardCapitalization.Sentences
    ),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    interactionSource: MutableInteractionSource =
        remember { MutableInteractionSource() },
    shape: Shape = MaterialTheme.shapes.large,
    clear: (() -> Unit)? = null,
    errorBottomText: String? = null,
    textOffset: Boolean = false,
    containerColor: Color = colorScheme.primaryContainer,
) {
    Column(modifier) {
        Card(
            Modifier.fillMaxWidth(),
            MaterialTheme.shapes.large,
            CardDefaults.cardColors(containerColor),
            border = if(isError)
                BorderStroke(1.dp, colorScheme.primary)
            else null
        ) {
            Box(Modifier, Alignment.TopEnd) {
                TextField(
                    value, { onValueChange(it) },
                    Modifier
                        .fillMaxWidth()
                        .padding(
                            end = clear?.let { 40.dp } ?: 0.dp
                        )
                        .offset(
                            y = if((maxLines > 1
                                        && !singleLine
                                        && value.isNotBlank())
                                || textOffset
                            ) 4.dp else 0.dp
                        ), enabled, readOnly,
                    textStyle, label, placeholder, leadingIcon,
                    trailingIcon, supportingText, isError,
                    visualTransformation, keyboardOptions,
                    keyboardActions, singleLine, maxLines,
                    interactionSource, shape, //colors
                )
                if(value.isNotEmpty()) clear?.let {
                    val single = singleLine || maxLines == 1
                    IconButton(
                        { it() }, Modifier
                            .align(
                                if(single) Alignment.CenterEnd
                                else Alignment.TopEnd
                            )
                            .padding(
                                top = if(single) 0.dp
                                else 4.dp
                            )
                    ) {
                        if(value.isNotEmpty()) {
                            Icon(
                                painterResource(R.drawable.ic_close),
                                "Clear",
                                Modifier.size(24.dp), colorScheme.scrim
                            )
                        }
                    }
                }
            }
        }
        errorBottomText?.let {
            if(isError) Text(
                it, Modifier.padding(
                    top = 5.dp,
                    start = 16.dp
                ), colorScheme.primary,
                style = typography.headlineSmall
            )
        }
    }
}

@Composable
fun textFieldLabel(
    label: Boolean = false,
    text: String? = null,
    labelFont: TextStyle = typography.headlineSmall,
    holderFont: TextStyle = typography.bodyMedium,
): @Composable (() -> Unit) {
    return {
        text?.let {
            Text(
                it, Modifier,
                style = if(label) labelFont
                else holderFont
            )
        }
    }
}
