package com.mikestf.planetsappmike.ui.register_user

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mikestf.planetsappmike.R
import com.mikestf.planetsappmike.domain.model.User
import com.mikestf.planetsappmike.ui.register_user.components.BirthDatePickerDialog
import com.mikestf.planetsappmike.ui.register_user.components.RegisterUserState
import com.mikestf.planetsappmike.ui.register_user.view_model.RegisterUserViewModel
import com.mikestf.planetsappmike.ui.theme.PurplePlanets
import com.mikestf.planetsappmike.utils.Countries
import com.mikestf.planetsappmike.utils.SnackBarUtil
import com.mikestf.planetsappmike.utils.SnackBarUtil.Companion.showSnackBar
import com.mikestf.planetsappmike.utils.getCountriesList
import com.mikestf.planetsappmike.utils.toFormattedDateString
import kotlinx.coroutines.delay
import java.util.Date

@Composable
fun RegisterUserScreen(
    onButtonClicked: () -> Unit,
    viewModel: RegisterUserViewModel = hiltViewModel()
) {
    AllContentScreen(onButtonClicked, viewModel)
}

@SuppressLint("SuspiciousIndentation")
@Composable
fun AllContentScreen(
    onButtonClicked: () -> Unit,
    viewModel: RegisterUserViewModel
) {
    var nameUser by rememberSaveable { mutableStateOf("") }
    var lastNameUser by rememberSaveable { mutableStateOf("") }
    var motherLastNameUser by rememberSaveable { mutableStateOf("") }
    var birthdate by rememberSaveable { mutableLongStateOf(Date().time) }
    var country by rememberSaveable { mutableStateOf(Countries.México.name) }
    val context = LocalContext.current

    val pattern = remember { Regex("[a-zA-z\\s]*") }

    LaunchedEffect(Unit) {
        viewModel
            .isUserSaved
            .collect {
                showSnackBar(
                    context.getString(
                        R.string.save_success
                    )
                )
                nameUser = ""
                lastNameUser = ""
                motherLastNameUser = ""
                delay(2000)
                onButtonClicked()
            }
    }

    LaunchedEffect(Unit) {
        viewModel
            .allUsers
            .collect {
                if (it.isNotEmpty()) {
                    onButtonClicked()
                }
            }
    }

    Scaffold (
        topBar = { TopBar() },
        bottomBar = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 16.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(PurplePlanets),
                onClick = {
                    validateFields(
                        name = nameUser,
                        lastname = lastNameUser,
                        motherLastName = motherLastNameUser,
                        birthdate = birthdate,
                        country = country,
                        onInvalidate = {
                            val invalidatedValue = context.getString(it)
                            showSnackBar(
                                context.getString(
                                    R.string.value_is_empty,
                                    invalidatedValue
                                )
                            )
                        },
                        onValidate = {
                            viewModel.insertUser(RegisterUserState(it))
                        },
                        viewModel = viewModel
                    )
                },
                shape = MaterialTheme.shapes.large
            ) {
                Text(
                    text = stringResource(id = R.string.save),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Box {
                SnackBarUtil.SnackBarWithoutScaffold(
                    SnackBarUtil.getSnackBarMessage().component1(),
                    SnackBarUtil.isSnackBarVisible().component1()
                ) {
                    SnackBarUtil.hideSnackBar()
                }
                AnimatedVisibility(
                    visible = true,
                    enter = slideInVertically(initialOffsetY = { it }),
                    exit = slideOutVertically(targetOffsetY = { it }),
                    content = {
                    }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Spacer(modifier = Modifier.padding(4.dp))

            Text(
                text = stringResource(id = R.string.name_user),
                style = MaterialTheme.typography.bodyLarge
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = nameUser,
                onValueChange = {
                    if (it.matches(pattern) && it.length <= 40)
                    nameUser = it},
                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(R.string.name_user_description)
                    )
                },
            )

            Spacer(modifier = Modifier.padding(4.dp))

            Text(
                text = stringResource(id = R.string.last_name_user),
                style = MaterialTheme.typography.bodyLarge
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = lastNameUser,
                onValueChange = {
                    if (it.matches(pattern) && it.length <= 30)
                        lastNameUser = it},
                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(R.string.last_name_user_description)
                    )
                },
            )

            Spacer(modifier = Modifier.padding(4.dp))

            Text(
                text = stringResource(id = R.string.mothers_last_name_user),
                style = MaterialTheme.typography.bodyLarge
            )
            TextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = motherLastNameUser,
                onValueChange = {
                    if (it.matches(pattern)&& it.length <= 30)
                        motherLastNameUser = it},
                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(R.string.mothers_last_name_user_description)
                    )
                },
            )

            Spacer(modifier = Modifier.padding(4.dp))

            BirthDateTextField { birthdate = it }

            Spacer(modifier = Modifier.padding(4.dp))

            CountryDropdownMenu { country = it }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryDropdownMenu(country: (String) -> Unit) {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = stringResource(id = R.string.country),
            style = MaterialTheme.typography.bodyLarge
        )

        val options = getCountriesList().map { it.name }
        var expanded by remember { mutableStateOf(false) }
        var selectedOptionText by remember { mutableStateOf(options[0]) }
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            TextField(
                modifier = Modifier.menuAnchor(),
                readOnly = true,
                value = selectedOptionText,
                onValueChange = {},
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                options.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption) },
                        onClick = {
                            selectedOptionText = selectionOption
                            country(selectionOption)
                            expanded = false
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthDateTextField(birthdate: (Long) -> Unit) {
    Text(
        text = stringResource(id = R.string.birth_date),
        style = MaterialTheme.typography.bodyLarge
    )

    var shouldDisplay by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()
    if (isPressed) {
        shouldDisplay = true
    }

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = System.currentTimeMillis().plus(86400000),
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return true
            }
        }
    )

    var selectedDate by rememberSaveable {
        mutableStateOf(
            datePickerState.selectedDateMillis?.toFormattedDateString() ?: ""
        )
    }

    BirthDatePickerDialog(
        state = datePickerState,
        shouldDisplay = shouldDisplay,
        onConfirmClicked = { selectedDateInMillis ->
            selectedDate = selectedDateInMillis.toFormattedDateString()
            birthdate(selectedDateInMillis)
        },
        dismissRequest = {
            shouldDisplay = false
        }
    )

    TextField(
        modifier = Modifier.fillMaxWidth(),
        readOnly = true,
        value = selectedDate,
        onValueChange = {},
        trailingIcon = { Icons.Default.DateRange },
        interactionSource = interactionSource
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar (
        colors = TopAppBarDefaults.largeTopAppBarColors(containerColor = PurplePlanets),
        title = {
            Text(
                text = stringResource(id = R.string.register_title),
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .wrapContentSize(Alignment.Center)
            )
        }
    )
}

private fun validateFields(
    name: String,
    lastname: String,
    motherLastName: String,
    birthdate: Long,
    country: String,
    onInvalidate: (Int) -> Unit,
    onValidate: (User) -> Unit,
    viewModel: RegisterUserViewModel
) {
    if (name.isEmpty()) {
        onInvalidate(R.string.name_user_validation)
        return
    }

    if (lastname.isEmpty()) {
        onInvalidate(R.string.last_name_user_validation)
        return
    }

    if (motherLastName.isEmpty()) {
        onInvalidate(R.string.mothers_last_name_user_validation)
        return
    }

    if (birthdate < 1) {
        onInvalidate(R.string.birth_date)
        return
    }

    if (country.isEmpty()) {
        onInvalidate(R.string.country)
        return
    }

    val newUser =
        viewModel.createUser(name, lastname, motherLastName, Date(birthdate), country)

    onValidate(newUser)
}