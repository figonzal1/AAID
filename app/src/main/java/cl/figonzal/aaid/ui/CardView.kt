package cl.figonzal.aaid.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ContentCopy
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.figonzal.aaid.BuildConfig
import cl.figonzal.aaid.R
import cl.figonzal.aaid.utils.Utils.copyToClipBoard
import cl.figonzal.aaid.utils.Utils.shareAAID

@Preview(showBackground = true)
@Composable
fun DefaultCardAAID() {
    CardAAID("91cf0b4c-578c-4e26-bb5a-10ca1ad1abe1")
}

@Composable
fun CardAAID(aaid: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Card(modifier = Modifier.fillMaxWidth()) {
            CardContent(aaid)
        }
    }
}

@Composable
private fun CardContent(aaid: String) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        val context = LocalContext.current
        val clipboardManager = LocalClipboardManager.current

        //Header section
        Box {

            Image(
                painterResource(R.drawable.undraw_android_jr64),
                contentDescription = stringResource(R.string.cd_background_image),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(bottom = 16.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                stringResource(R.string.cv_title),
                style = MaterialTheme.typography.headlineMedium
            )
        }

        //Subtitle
        Text(
            text = stringResource(R.string.cv_subtitle),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        //AAID
        Text(
            text = aaid,
            style = MaterialTheme.typography.labelLarge,
            fontFamily = FontFamily.Monospace,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 32.dp)
        )

        ActionsButtons(context, clipboardManager, aaid)
    }
}

@Composable
private fun ActionsButtons(context: Context, clipboardManager: ClipboardManager, aaid: String) {
    Row {

        Box(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.align(Alignment.BottomStart),
                text = "v${BuildConfig.VERSION_NAME}",
                style = MaterialTheme.typography.bodySmall,
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
            )
            Row(
                modifier = Modifier.align(Alignment.BottomEnd),
                verticalAlignment = Alignment.Bottom
            ) {
                ClipBoardButton(context, clipboardManager, aaid)
                ShareButton(context, clipboardManager)
            }
        }
    }
}


@Composable
private fun ClipBoardButton(
    context: Context,
    clipboardManager: ClipboardManager,
    aaid: String
) {

    Button(
        onClick = { copyToClipBoard(context, clipboardManager, aaid) },
        contentPadding = ButtonDefaults.ButtonWithIconContentPadding,
        modifier = Modifier.padding(end = 8.dp)
    ) {
        Icon(Icons.Rounded.ContentCopy, stringResource(R.string.cd_copy_btn))
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(stringResource(R.string.btn_copy))
    }
}

@Composable
private fun ShareButton(
    context: Context,
    clipboardManager: ClipboardManager
) {
    Button(
        onClick = { shareAAID(context, clipboardManager) },
        contentPadding = ButtonDefaults.ButtonWithIconContentPadding
    ) {
        Icon(Icons.Rounded.Share, stringResource(R.string.cd_share_id_btn))
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(stringResource(R.string.btn_share))
    }
}