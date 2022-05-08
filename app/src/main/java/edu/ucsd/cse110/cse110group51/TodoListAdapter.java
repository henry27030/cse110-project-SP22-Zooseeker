package edu.ucsd.cse110.cse110group51;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {
    private List<TodoListItem> todoItems = Collections.emptyList();
    private Consumer<TodoListItem> onDeleteClicked;
    private BiConsumer<TodoListItem, String> onTextEditedHandler;

    public void setTodoListItems(List<TodoListItem> newTodoItems) {
        this.todoItems.clear();
        this.todoItems=newTodoItems;
        notifyDataSetChanged();
    }

    public void setOnDeleteClickedHandler (Consumer<TodoListItem> onDeleteClicked) {
        this.onDeleteClicked = onDeleteClicked;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.todo_list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setTodoItem(todoItems.get(position));
    }

    @Override
    public int getItemCount() { return todoItems.size(); }

    @Override
    public long getItemId(int position) {return todoItems.get(position).id;}

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final TextView deleted;
        private final CheckBox checkBox;
        private TodoListItem todoItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textView=itemView.findViewById(R.id.todo_item_text);
            this.deleted=itemView.findViewById(R.id.delete_btn);
            this.checkBox = itemView.findViewById(R.id.completed);
            // in exhibitList, there is no checkbox needed
            this.deleted.setVisibility(View.VISIBLE);
            this.checkBox.setVisibility(View.INVISIBLE);

            this.deleted.setOnClickListener(view-> {
                if (onDeleteClicked == null) return;
                onDeleteClicked.accept(todoItem);
            });
        }

        public TodoListItem getTodoItem() {return todoItem; }

        public void setTodoItem(TodoListItem todoItem) {
            this.todoItem = todoItem;
            this.textView.setText(todoItem.text);
        }

    }
}
