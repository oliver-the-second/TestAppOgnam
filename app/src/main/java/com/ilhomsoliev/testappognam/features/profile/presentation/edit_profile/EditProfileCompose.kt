package com.ilhomsoliev.testappognam.features.profile.presentation.edit_profile

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import android.util.Base64
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ilhomsoliev.testappognam.R
import com.ilhomsoliev.testappognam.core.Constants
import com.ilhomsoliev.testappognam.shared.components.ImageBox
import com.ilhomsoliev.testappognam.shared.components.Loader
import java.io.ByteArrayOutputStream
import java.io.IOException


data class EditProfileState(
    val name: String,
    val username: String,
    val vk: String,
    val instagram: String,
    val phone: String,
    val last: String,
    val isOnline: Boolean,
    val city: String,
    val birthday: String,
    val isLoading: Boolean,
    val avatar: Pair<String, String>,
    val avatarUrl: String?
)

interface EditProfileCallback {
    fun onLogout()
    fun onBack()
    fun onSave()
    fun onNameChange(value: String)
    fun onLastChange(value: String)
    fun onVkChange(value: String)
    fun onInstagramChange(value: String)
    fun onCityChange(value: String)
    fun onBirthdayChange(value: String)
    fun onAvatarChange(value: Pair<String, String>)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileContent(
    state: EditProfileState,
    callback: EditProfileCallback
) {
    val context = LocalContext.current

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri: Uri? ->
            uri?.let {
                val (name, base64) = getBase64ForUriAndPossiblyCrash(context, uri)
                callback.onAvatarChange(name to base64)
            }
        }
    )

    Scaffold(topBar = {
        TopAppBar(title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    callback.onBack()
                }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
                Text(
                    text = "Edit Profile", fontFamily = FontFamily(
                        Font(R.font.roboto_regular)
                    )
                )
            }
        })
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 12.dp)
                .padding(it)
        ) {
            //
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                ImageBox(
                    uri = decodeBase64(context, state.avatar.second),
                    imageUrl = (Constants.HOST + state.avatarUrl),
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                        .padding(top = 12.dp)
                        .clickable {
                            singlePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        }
                )
            }
            Text(
                text = "Click to image to edit", fontFamily = FontFamily(
                    Font(R.font.roboto_regular)
                ), fontSize = 12.sp
            )
            //
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.phone,
                onValueChange = {},
                enabled = false,
                label = {
                    Text(
                        text = "Phone", color = Color.Gray, fontFamily = FontFamily(
                            Font(R.font.roboto_regular)
                        )
                    )
                }
            )
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.username,
                enabled = false,
                onValueChange = {},
                label = {
                    Text(
                        text = "Username", color = Color.Gray, fontFamily = FontFamily(
                            Font(R.font.roboto_regular)
                        )
                    )
                })
            Spacer(modifier = Modifier.height(12.dp))
            TextField(modifier = Modifier.fillMaxWidth(), value = state.name, onValueChange = {
                callback.onNameChange(it)
            }, label = {
                Text(
                    text = "Name", color = Color.Gray, fontFamily = FontFamily(
                        Font(R.font.roboto_regular)
                    )
                )
            })
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.city,
                onValueChange = {
                    callback.onCityChange(it)
                },
                label = {
                    Text(
                        text = "City", color = Color.Gray, fontFamily = FontFamily(
                            Font(R.font.roboto_regular)
                        )
                    )
                })
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.instagram,
                onValueChange = {
                    callback.onInstagramChange(it)

                },
                label = {
                    Text(
                        text = "Instagram", color = Color.Gray, fontFamily = FontFamily(
                            Font(R.font.roboto_regular)
                        )
                    )
                })
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.vk,
                onValueChange = {
                    callback.onVkChange(it)
                },
                label = {
                    Text(
                        text = "Vk", color = Color.Gray, fontFamily = FontFamily(
                            Font(R.font.roboto_regular)
                        )
                    )
                })
            Spacer(modifier = Modifier.height(12.dp))
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = state.birthday,
                onValueChange = {
                    callback.onBirthdayChange(it)
                },
                label = {
                    Text(
                        text = "Birthday", color = Color.Gray, fontFamily = FontFamily(
                            Font(R.font.roboto_regular)
                        )
                    )
                })
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    callback.onSave()
                }, colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFE90202)
                )
            ) {
                Text(
                    modifier = Modifier.padding(12.dp),
                    text = "Save",
                    fontSize = 26.sp,
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(120.dp))
        }
    }
    AnimatedVisibility(visible = state.isLoading) {
        Loader()
    }

}

private fun getBase64ForUriAndPossiblyCrash(context: Context, uri: Uri): Pair<String, String> {
    return try {
        val bytes = context.contentResolver.openInputStream(uri)?.readBytes()
        getImageName(uri) to Base64.encodeToString(bytes, Base64.DEFAULT)
    } catch (error: IOException) {
        error.printStackTrace() // This exception always occurs
        "" to ""
    }
}

private fun getImageName(uri: Uri): String {
    var result = uri.path
    val cut = result?.lastIndexOf('/')
    cut?.let {
        if (cut != -1) {
            result = result?.substring(cut + 1)
        }
    }

    return result ?: ""

}

private fun decodeBase64(context: Context, base64: String): Uri? {
    if (base64.isEmpty()) return null
    val bytes: ByteArray = Base64.decode(base64, Base64.DEFAULT)
    val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    return getImageUriFromBitmap(context, bitmap)
}

fun getImageUriFromBitmap(inContext: Context, inImage: Bitmap): Uri? {
    val bytes = ByteArrayOutputStream()
    inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
    val path =
        MediaStore.Images.Media.insertImage(inContext.contentResolver, inImage, "Title", null)
    return Uri.parse(path)
}