package com.plcoding.onboarding_presentation.welcome

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.plcoding.core.R
import com.plcoding.core_ui.LocalSpacing
import com.plcoding.onboarding_presentation.components.ActionButton


@Composable
fun WelcomeScreen(
    onNextClick : () -> Unit
    ) {
    val dimens = LocalSpacing.current
    Column(
        modifier = Modifier.fillMaxSize().padding(dimens.spaceMedium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Text(
            text = stringResource(id =  R.string.welcome_text),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(dimens.spaceMedium))
        ActionButton(
            onClick = {
                      onNextClick()
            },
            text = stringResource(id = R.string.next),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

    }

}