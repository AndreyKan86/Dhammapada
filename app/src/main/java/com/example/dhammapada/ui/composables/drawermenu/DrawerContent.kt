package com.example.dhammapada.ui.composables.drawermenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.dhammapada.R
import com.example.dhammapada.ui.theme.COMPANY_NAME
import com.example.dhammapada.ui.theme.CompanyNameTextStyle
import com.example.dhammapada.ui.theme.DESCRIPTION_APP
import com.example.dhammapada.ui.theme.LINK
import com.example.dhammapada.ui.theme.LINK_FOR_SOURSE
import com.example.dhammapada.ui.theme.LOGO_DESCRIPTION
import com.example.dhammapada.ui.theme.TextStyle

//Содержимое выплывающего меню
@Composable
fun DrawerContent(onItemClick: () -> Unit)
{
    val uriHandler = LocalUriHandler.current

    ModalDrawerSheet(
        drawerContainerColor = MaterialTheme.colorScheme.surface,
    )
    {
        ConstraintLayout()
        {
            val (logo, nameCompany, upDivider, bottomDivider, textDescription, themeswitch) = createRefs()

            Box(
                modifier = Modifier.constrainAs(themeswitch) {
                    top.linkTo(parent.top, margin = 16.dp)
                    end.linkTo(parent.end, margin = 16.dp)
                }
            ) {
                ThemeSwitcher()
            }
            Box(
                modifier = Modifier.constrainAs(logo){
                    top.linkTo(parent.top, margin = 16.dp)
                    start.linkTo(parent.start, margin = 16.dp)
                }
            )
            {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = LOGO_DESCRIPTION,
                    modifier = Modifier
                        .size(80.dp)
                        .border(2.dp, MaterialTheme.colorScheme.primary, CircleShape)
                        .clip(CircleShape)
                        .clickable { uriHandler.openUri(LINK) }
                )
            }

            Box(
                modifier = Modifier.constrainAs(nameCompany)
                {
                    top.linkTo(parent.top, margin = 40.dp)
                    start.linkTo(logo.end, margin = 16.dp)
                }
            )
            {
                Text(
                    text = COMPANY_NAME,
                    style = CompanyNameTextStyle,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(upDivider) {
                        top.linkTo(parent.top, margin = 8.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                color = Color.DarkGray,
                thickness = 2.dp
            )

            HorizontalDivider(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(bottomDivider)
                    {
                        top.linkTo(logo.bottom, margin = 6.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    },
                color = Color.DarkGray,
                thickness = 2.dp
            )

            Box(modifier = Modifier
                .constrainAs(textDescription)
                {
                    top.linkTo(bottomDivider.top, margin = 12.dp)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(16.dp)
            )
            {
                TextLink(
                    fullText = "$DESCRIPTION_APP $LINK_FOR_SOURSE",
                    linkText = LINK_FOR_SOURSE,
                    link = "https://t.me/buddha_dhammapada",
                    TextStyle
                )
            }
        }
    }
}
