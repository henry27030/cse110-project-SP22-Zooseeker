//package edu.ucsd.cse110.cse110group51;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.CheckBox;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.core.util.Consumer;
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.Collections;
//import java.util.List;
//
//public class ExhibitAdapter extends RecyclerView.Adapter<ExhibitAdapter.ViewHolder> {
//    // private fields
//    private List<TodoListItem> todoItems = Collections.emptyList();
//    private Consumer<TodoListItem> onCheckBoxClicked;
//
//    public void setTodoListItems(List<TodoListItem> newTodoItems) {
//        this.todoItems.clear();
//        this.todoItems = newTodoItems;
//        notifyDataSetChanged();
//    }
//
//    public void setOnCheckBoxClickedHandler(Consumer<TodoListItem> onCheckBoxClicked){
//        this.onCheckBoxClicked = onCheckBoxClicked;
//    }
//
//    @NonNull
//    @Override
//    public ExhibitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater
//                .from(parent.getContext())
//                .inflate(R.layout.todo_list_item, parent, false);
//
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.setTodoItem(todoItems.get(position));
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        private final TextView textView;
//        private final CheckBox checkBox;
//        private final TextView deleted;
//        private TodoListItem todoItem;
//
//        public ViewHolder(View view) {
//            super(view);
//            this.textView = itemView.findViewById(R.id.todo_item_text);
//            this.checkBox = itemView.findViewById(R.id.completed);
//            this.deleted=itemView.findViewById(R.id.delete_btn);
//            // we dont need delete button in exhibit views
//            this.checkBox.setVisibility(View.VISIBLE);
//            this.deleted.setVisibility(View.INVISIBLE);
//
//            this.checkBox.setOnClickListener(itemView -> {
//                if(onCheckBoxClicked == null){
//                    return;
//                }
//                onCheckBoxClicked.accept(todoItem);
//            });
//        }
//
//
//        public TodoListItem getTodoItem() { return todoItem; }
//
//        public void setTodoItem(TodoListItem todoItem) {
//            this.todoItem = todoItem;
//            this.textView.setText(todoItem.text);
//        }
//    }
//}
