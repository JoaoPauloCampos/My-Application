package com.jnicomedes.myapplication.ui.url_shortener

import androidx.annotation.StringRes
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.jnicomedes.myapplication.R
import com.jnicomedes.myapplication.data.domain.model.UrlData
import com.jnicomedes.myapplication.ui.theme.Purple500
import org.koin.androidx.compose.koinViewModel

@Composable
fun UrlShortenerScreen(viewModel: UrlShortenerViewModel = koinViewModel()) {
    SearchScreen(viewModel)
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(viewModel: UrlShortenerViewModel) {
    val inputText = viewModel.inputText.collectAsState()
    val list = viewModel.urlList.collectAsState()
    val urlShortenState: ShortenUrlState by remember { viewModel.urlShortenMutableState }
    val isHidedLoading = urlShortenState !is ShortenUrlState.Loading
    val keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            SearchBar(
                keyboardController,
                modifier = Modifier.weight(1f),
                searchText = inputText.value,
                errorMessage = urlShortenState.errorMessage(),
                isEnabled = isHidedLoading,
                viewModel = viewModel,
            ) {
                viewModel.setInputText(it)
            }
            ShortenUrlButton(isEnabled = isHidedLoading) {
                keyboardController?.hide()
                viewModel.shortenUrl()
            }
        }
        UrlList(urlList = list.value)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    keyboardController: SoftwareKeyboardController?,
    modifier: Modifier,
    searchText: String,
    @StringRes errorMessage: Int?,
    viewModel: UrlShortenerViewModel,
    isEnabled: Boolean,
    onSearchTextChanged: (String) -> Unit
) {
    Column(modifier = modifier) {
        TextField(
            modifier = Modifier
                .border(1.dp, Color.LightGray, CircleShape)
                .fillMaxWidth()
                .testTag("UrlTextField"),
            value = searchText,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                disabledTextColor = Color.Gray,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                errorIndicatorColor = Color.Transparent,
            ),
            onValueChange = onSearchTextChanged,
            singleLine = true,
            isError = errorMessage != null,
            enabled = isEnabled,
            label = {
                Text(text = stringResource(R.string.type_the_url))
            },
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    viewModel.shortenUrl()
                }
            )
        )
        errorMessage?.let {
            Text(
                text = stringResource(id = errorMessage),
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .testTag("TextError")
            )
        }
    }
}

@Composable
fun ShortenUrlButton(
    isEnabled: Boolean,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = isEnabled,
        modifier = Modifier
            .padding(start = 8.dp)
            .height(50.dp)
            .width(50.dp)
            .testTag("ShortenUrlButton"),
        shape = RoundedCornerShape(50),
    ) {
        if (isEnabled)
            Icon(imageVector = Icons.Default.Send, contentDescription = "Shorten Url")
        else
            CircularProgressIndicator(
                modifier = Modifier.padding(top = 8.dp), strokeWidth = 2.dp, color = Purple500
            )
    }
}

@Composable
fun UrlList(urlList: List<UrlData>) {
    LazyColumn {
        items(urlList) { item ->
            Card(
                shape = RoundedCornerShape(50),
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .border(1.dp, Color.LightGray, shape = CircleShape)
                    .testTag("UrlCardItem")
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.original_url, item.originalUrl),
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .testTag("OriginalUrl")
                    )
                    Text(
                        text = stringResource(id = R.string.shortened_url, item.shortenedUrl),
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                            .testTag("ShortUrl")
                    )
                }
            }
        }
    }
}
