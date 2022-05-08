package edu.ucsd.cse110.cse110group51;

import android.app.Application;
import android.content.Context;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TodoListViewModel extends AndroidViewModel {
    private LiveData<List<TodoListItem>> todoListItems;
    private final TodoListItemDao todoListItemDao;

    public TodoListViewModel(@NonNull Application application) {
        super(application);
        Context context = getApplication().getApplicationContext();
        TodoDatabase db = TodoDatabase.getSingleton(context);
        todoListItemDao = db.todoListItemDao();
    }

    public LiveData<List<TodoListItem>> getTodoListItems() {
        if (todoListItems==null) {
            loadUsers();
        }
        return todoListItems;
    }

    private void loadUsers() {
        todoListItems = todoListItemDao.getAllLive();
    }

    public void toggleCompleted(TodoListItem todoListItem, CheckBox checkBox) {
        if(checkBox.isChecked()){
            deleteTodo(todoListItem);
        }else{
            createTodo(todoListItem.text);
        }
    }

    public void updateText(TodoListItem todoListItem, String newText) {
        todoListItem.text = newText;
        todoListItemDao.update(todoListItem);
    }

    public void createTodo (String text) {
        int endOfListOrder = todoListItemDao.getOrderForAppend();
        TodoListItem newItem = new TodoListItem(text, endOfListOrder);
        todoListItemDao.insert(newItem);
    }

    public void deleteTodo(TodoListItem todoListItem) {
        todoListItemDao.delete(todoListItem);
    }
}
