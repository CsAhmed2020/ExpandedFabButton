package com.csahmed.laundryapp.recipt_feature.presentation.component.fab

import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.csahmed.laundryapp.core.theme.mySecondaryColor

@ExperimentalAnimationApi
@Composable
fun MultiFloatingActionButton(
    modifier: Modifier = Modifier,
    items: List<FabItem>, //all fab items items in expanded state
    fabState: MutableState<MultiFabState> = rememberMultiFabState(),
    fabIcon: FabIcon, //main fab icon
    fabOption: FabOption = FabOption(), //with default values
    onFabItemClicked: (fabItem: FabItem) -> Unit,
    stateChanged: (fabState: MultiFabState) -> Unit = {}
) {
    //when state change rotate the icon by $iconRotate degree
    val rotation by animateFloatAsState(
        if (fabState.value == MultiFabState.Expand) {
            fabIcon.iconRotate ?: 0f
        } else {
            0f
        }
    )

    Column(
        modifier = modifier.wrapContentSize(),
        horizontalAlignment = Alignment.End
    ) {
        AnimatedVisibility(
            visible = fabState.value.isExpanded(),
            enter = fadeIn() + expandVertically(),
            exit = fadeOut()
        ) {
            LazyColumn(
                modifier = Modifier.wrapContentSize(),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(items.size) { index ->
                    FabItemUi(
                        item = items[index],
                        fabOption = fabOption,
                        onFabItemClicked = {
                            if (it.id ==1) { //this is first fab button that change the state when expanded
                                fabState.value = fabState.value.toggleValue()
                                stateChanged(fabState.value)
                            }
                            //pass clicked item [to distinguish the fab button ]
                            onFabItemClicked.invoke(it)
                        }
                    )
                }

                item {}
            }
        }

        // this is main fab button
        FloatingActionButton(
            onClick = {
                fabState.value = fabState.value.toggleValue()
                stateChanged(fabState.value)
            },
            backgroundColor = mySecondaryColor,
            contentColor = fabOption.iconTint
        ) {
            Icon(
                imageVector = fabIcon.iconRes,
                contentDescription = "FAB",
                modifier = Modifier.rotate(rotation),
                tint = fabOption.iconTint
            )
        }
    }
}

@Composable
fun FabItemUi(
    item: FabItem,
    fabOption: FabOption,
    onFabItemClicked: (item: FabItem) -> Unit
) {
    Row(
        modifier = Modifier
            .wrapContentSize()
            .padding(end = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (fabOption.showLabel) {
            Text(
                text = item.label,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 6.dp, vertical = 4.dp).background(color = mySecondaryColor)
            )
        }

        FloatingActionButton(
            onClick = {
                onFabItemClicked(item)
            },
            modifier = Modifier.size(40.dp),
            backgroundColor = mySecondaryColor,
            contentColor = fabOption.iconTint
        ) {
            Icon(
                imageVector =  item.iconRes,
                contentDescription = "Float Icon",
                tint = fabOption.iconTint
            )
        }
    }
}