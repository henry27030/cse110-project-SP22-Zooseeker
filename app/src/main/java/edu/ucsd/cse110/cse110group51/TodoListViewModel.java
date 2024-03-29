package edu.ucsd.cse110.cse110group51;

import android.app.Application;
import android.content.Context;
import android.util.Log;
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
        if (todoListItems==null) {
            loadUsers();
        }
    }

    public LiveData<List<TodoListItem>> getTodoListItems() {
        if (todoListItems==null) {
            loadUsers();
        }
        return todoListItems;
    }

    public List<TodoListItem> getCurrentItems(){
        return todoListItemDao.getAll();
    }

    private void loadUsers() {
        todoListItems = todoListItemDao.getAllLive();
    }

    public void toggleCompleted(TodoListItem todoListItem, CheckBox checkBox) {
        if(checkBox.isChecked()){
            createTodo(todoListItem.text);
        }else if(MainActivity.exhibitList.contains(todoListItem.text)){
            deleteTodo(todoListItem);
        }
    }

    public void updateText(TodoListItem todoListItem, String newText) {
        todoListItem.text = newText;
        todoListItemDao.update(todoListItem);
    }

    public void createTodo (String text) {
        int endOfListOrder = todoListItemDao.getOrderForAppend();
        TodoListItem newItem = new TodoListItem(text, endOfListOrder);
        MainActivity.exhibitList.add(newItem.text);
        Log.v("CreateToDo", String.join(", ", MainActivity.exhibitList));
        todoListItemDao.insert(newItem);
    }

    public void deleteTodo(TodoListItem todoListItem) {
        MainActivity.exhibitList.remove(todoListItem.text);
        Log.v("DeleteToDo", String.join(", ", MainActivity.exhibitList));
//        MainActivity.viewModel.getCurrentItems().indexOf()
        for(int i = 0; i < MainActivity.viewModel.getCurrentItems().size(); i++){
            if(MainActivity.viewModel.getCurrentItems().get(i).text.equals(todoListItem.text)){
                todoListItemDao.delete(MainActivity.viewModel.getCurrentItems().get(i));
                break;
            }
        }
    }
}
