package cl.figonzal.aaid

import android.content.Context
import android.content.Intent
import android.widget.Toast
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
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)
@Composable
fun CardAAID() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Card(modifier = Modifier.fillMaxWidth()) {
            CardContent()
        }
    }
}

@Composable
private fun CardContent() {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

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
                stringResource(R.string.card_title),
                style = MaterialTheme.typography.headlineMedium
            )
        }

        //Subtitle
        Text(
            text = stringResource(R.string.subtitle),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Text(
                text = "asd123-asdgasd-123123-asd123",
                style = MaterialTheme.typography.labelLarge,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold
            )
            Icon(
                Icons.Rounded.ContentCopy,
                contentDescription = stringResource(R.string.cd_icon_copy_content),
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        ActionsButtons()
    }
}

@Composable
private fun ActionsButtons() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {

        with(LocalContext.current) {

            val clipboardManager = LocalClipboardManager.current

            ClipBoardButton(clipboardManager, this)
            ShareButton(clipboardManager, this)
        }
    }
}

@Composable
private fun ClipBoardButton(
    clipboardManager: ClipboardManager,
    context: Context
) {

    val copyToClipBoardText = stringResource(R.string.copy_to_clipboard)
    Button(
        onClick = {
            clipboardManager.setText(AnnotatedString("aaid"))
            Toast.makeText(context, copyToClipBoardText, Toast.LENGTH_SHORT).show()
        },
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
    clipboardManager: ClipboardManager,
    context: Context
) {
    val intentChooserText = stringResource(R.string.intent_chooser)

    Button(
        onClick = {
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, clipboardManager.getText())
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, intentChooserText)
            context.startActivity(shareIntent)
        },
        contentPadding = ButtonDefaults.ButtonWithIconContentPadding
    ) {
        Icon(Icons.Rounded.Share, stringResource(R.string.cd_share_id_btn))
        Spacer(Modifier.size(ButtonDefaults.IconSpacing))
        Text(stringResource(R.string.btn_share))
    }
}
