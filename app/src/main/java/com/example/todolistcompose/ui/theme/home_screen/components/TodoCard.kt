package com.example.todolistcompose.ui.theme.home_screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.todolistcompose.domain.model.Todo
import com.example.todolistcompose.util.taskTextStyle

@Composable
fun TodoCard(
    todo: Todo,
    onDone: () -> Unit,
    onUpdate: (id: Int) -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onDone.invoke() }, modifier = Modifier.weight(1f)) {
                Icon(imageVector = Icons.Rounded.Check, contentDescription = null)
            }
            todo.title?.let {
                if (it.length>5){
                    Text(
                        text = it.substring(0,5)+".." ,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(3f).padding(start = 10.dp),
                        style = taskTextStyle,
                    )
                }else{
                    Text(
                        text = it ,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(3f).padding(start = 10.dp),
                        style = taskTextStyle,
                    )
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))

            todo.description?.let {
                if (it.length>10){
                    Text(
                        text = it.substring(0,10)+"...",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(8f),
                        style = taskTextStyle
                    )
                }else{
                    Text(
                        text = it,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.weight(8f),
                        style = taskTextStyle
                    )
                }
            }

            if (todo.isImportant == true) {
                Icon(
                    imageVector = Icons.Rounded.Star,
                    contentDescription = null,
                    modifier = Modifier.weight(1f).padding(end = 5.dp)
                )
            }

            IconButton(
                onClick = { onUpdate.invoke(todo.id) },
                modifier = Modifier.weight(1f)
            ) {
                Icon(imageVector = Icons.Rounded.Edit, contentDescription = null)
            }
        }
    }
}