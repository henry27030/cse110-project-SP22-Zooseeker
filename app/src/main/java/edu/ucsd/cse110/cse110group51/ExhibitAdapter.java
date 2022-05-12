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

public class ExhibitAdapter extends RecyclerView.Adapter<ExhibitAdapter.ViewHolder> {
    // private fields
    private List<TodoListItem> todoItems = Collections.emptyList();
    private BiConsumer<TodoListItem, CheckBox> onCheckBoxClicked;

    public void setTodoListItems(List<TodoListItem> newTodoItems) {
        this.todoItems.clear();
        this.todoItems = newTodoItems;
//        notifyDataSetChanged();
    }

    public void setOnCheckBoxClickedHandler(BiConsumer<TodoListItem, CheckBox> onCheckBoxClicked){
        this.onCheckBoxClicked = onCheckBoxClicked;
    }

    @NonNull
    @Override
    public ExhibitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
    public int getItemCount() {
        return todoItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final CheckBox checkBox;
        private final TextView deleted;
        private TodoListItem todoItem;

        public ViewHolder(View view) {
            super(view);
            this.textView = itemView.findViewById(R.id.todo_item_text);
            this.checkBox = itemView.findViewById(R.id.completed);
            this.deleted = itemView.findViewById(R.id.delete_btn);

            // in exhibitViewListUnderCategory, checkBox is visible and deleted is invisible
            this.checkBox.setVisibility(View.VISIBLE);
            this.deleted.setVisibility(View.INVISIBLE);

            // decide if the viewHolder is checked or unchecked
            String exhibitName = String.valueOf(textView.getText());
//            Log.v("ViewHolder", exhibitName);
            if(MainActivity.exhibitList.contains(exhibitName) &&
                    this.checkBox.getVisibility() == View.VISIBLE){
                 this.checkBox.setChecked(true);

            }else if(!MainActivity.exhibitList.contains(exhibitName) &&
                    this.checkBox.getVisibility() == View.VISIBLE){
                this.checkBox.setChecked(false);
            }

            this.checkBox.setOnClickListener(itemView -> {
                if(onCheckBoxClicked == null){
                    return;
                }
                onCheckBoxClicked.accept(todoItem, checkBox);
            });
        }


        public TodoListItem getTodoItem() { return todoItem; }

        public void setTodoItem(TodoListItem todoItem) {
            this.todoItem = todoItem;
            this.textView.setText(todoItem.text);
            if(MainActivity.exhibitList.contains(todoItem.text)){
                this.checkBox.setChecked(true);
            }
        }
    }
}
