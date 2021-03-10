package com.example.cbr.adapters;

import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbr.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Base class for adapters that display information, currently supports headers, dividers, and two text fields
 * Subclasses should implement generateList() by calling addTextViewHolder(), addDivider(), etc in order of appearance in generateList()
 */
public abstract class BaseInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    LayoutInflater layoutInflater;
    private final ArrayList<ViewHolderData> infoList = new ArrayList<>();

    final int SINGLE_TEXT_VIEW_TYPE = 0;
    final int DOUBLE_TEXT_VIEW_TYPE = 1;
    final int HEADER_VIEW_TYPE = 2;
    final int CLICKABLE_VIEW_TYPE = 3;
    final int DIVIDER_VIEW_TYPE = 4;
    final int EDIT_TEXT_VIEW_TYPE = 5;
    final int RADIO_GROUP_VIEW_TYPE = 6;
    final int SPINNER_VIEW_TYPE = 7;

    BaseInfoAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    /**
     * Implement and call generateList() in the subclass
     */
    abstract void generateList();

    void addSingleTextViewType(String text) {
        infoList.add(new SingleTextViewHolderData(text));
    }

    void addDoubleTextViewType(String firstText, String secondText) {
        infoList.add(new DoubleTextViewHolderData(firstText, secondText));
    }

    void addHeaderViewType(String text) {
        infoList.add(new HeaderViewHolderData(text));
    }

    void addClickableViewType(String text, ClickableViewHolderBehavior clickableViewHolderBehavior) {
        infoList.add(new ClickableViewHolderData(text, clickableViewHolderBehavior));
    }

    void addDividerViewType() {
        infoList.add(new DividerViewHolderData());
    }

    void addEditTextViewType(String questionText, String hintText, int inputType) {
        infoList.add(new EditTextViewHolderData(questionText, hintText, inputType));
    }

    void addRadioGroupViewType(String questionText, boolean isVertical, List<RadioGroupListItem> radioGroupListItems) {
        infoList.add(new RadioGroupViewHolderData(questionText, isVertical, radioGroupListItems));
    }

    void addSpinnerViewType(String questionText, List<String> optionsList) {
        infoList.add(new SpinnerViewHolderData(questionText, optionsList));
    }

    String boolToText(Boolean bool) {
        if (bool == null) {
            return context.getString(R.string.na);
        } else if (bool) {
            return context.getString(R.string.yes);
        } else {
            return context.getString(R.string.no);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case DOUBLE_TEXT_VIEW_TYPE:
                return new DoubleTextViewHolder(layoutInflater.inflate(R.layout.recyclerview_doubletext, parent, false));
            case HEADER_VIEW_TYPE:
                return new HeaderViewHolder(layoutInflater.inflate(R.layout.recyclerview_header, parent, false));
            case CLICKABLE_VIEW_TYPE:
                return new ClickableViewHolder(layoutInflater.inflate(R.layout.recyclerview_clickable, parent, false));
            case DIVIDER_VIEW_TYPE:
                return new DividerViewHolder(layoutInflater.inflate(R.layout.recyclerview_divider, parent, false));
        }
        return new DoubleTextViewHolder(layoutInflater.inflate(R.layout.recyclerview_doubletext, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case DOUBLE_TEXT_VIEW_TYPE:
                ((DoubleTextViewHolder) holder).bind((DoubleTextViewHolderData) infoList.get(position));
                break;
            case HEADER_VIEW_TYPE:
                ((HeaderViewHolder) holder).bind((HeaderViewHolderData) infoList.get(position));
                break;
            case CLICKABLE_VIEW_TYPE:
                ((ClickableViewHolder) holder).bind((ClickableViewHolderData) infoList.get(position));
            case DIVIDER_VIEW_TYPE:
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return infoList.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

    // VIEW HOLDERS ----------------------------------------------------------------------------------------------

    public class ViewHolderData implements Serializable {
        private final int viewType;

        ViewHolderData(int viewType) {
            this.viewType = viewType;
        }

        public int getViewType() {
            return viewType;
        }
    }

    // SINGLE TEXT VIEW ----------------------------------------------------------------------------------------------

    class SingleTextViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public SingleTextViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recyclerview_text);
        }

        public void bind(SingleTextViewHolderData singleTextViewHolderData) {
            textView.setText(singleTextViewHolderData.getText());
        }
    }

    class SingleTextViewHolderData extends ViewHolderData {
        private final String text;

        SingleTextViewHolderData(String text) {
            super(SINGLE_TEXT_VIEW_TYPE);
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    // DOUBLE TEXT VIEW ----------------------------------------------------------------------------------------------

    class DoubleTextViewHolder extends RecyclerView.ViewHolder {
        private final TextView firstTextView;
        private final TextView secondTextView;

        public DoubleTextViewHolder(View itemView) {
            super(itemView);
            firstTextView = itemView.findViewById(R.id.recyclerview_firstText);
            secondTextView = itemView.findViewById(R.id.recyclerview_edittext);
        }

        public void bind(DoubleTextViewHolderData doubleTextViewHolderData) {
            firstTextView.setText(doubleTextViewHolderData.getFirstText());

            if (doubleTextViewHolderData.getSecondText() == null || doubleTextViewHolderData.getSecondText().isEmpty()) {
                secondTextView.setText(context.getString(R.string.na));
            } else {
                secondTextView.setText(doubleTextViewHolderData.getSecondText());
            }
        }
    }

    class DoubleTextViewHolderData extends ViewHolderData {
        private final String firstText;
        private final String secondText;

        DoubleTextViewHolderData(String firstText, String secondText) {
            super(DOUBLE_TEXT_VIEW_TYPE);
            this.firstText = firstText;
            this.secondText = secondText;
        }

        public String getFirstText() {
            return firstText;
        }

        public String getSecondText() {
            return secondText;
        }
    }

    // HEADER ----------------------------------------------------------------------------------------------

    class HeaderViewHolder extends RecyclerView.ViewHolder {
        private final TextView headerTextView;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            headerTextView = itemView.findViewById(R.id.recyclerview_headerText);
        }

        public void bind(HeaderViewHolderData headerViewHolderData) {
            headerTextView.setText(headerViewHolderData.getHeaderText());
        }
    }

    class HeaderViewHolderData extends ViewHolderData {
        private final String headerText;

        HeaderViewHolderData(String headerText) {
            super(HEADER_VIEW_TYPE);
            this.headerText = headerText;
        }

        public String getHeaderText() {
            return headerText;
        }
    }

    // CLICKABLE ----------------------------------------------------------------------------------------------

    class ClickableViewHolder extends RecyclerView.ViewHolder {
        private final TextView clickableTextView;

        public ClickableViewHolder(@NonNull View itemView) {
            super(itemView);
            clickableTextView = itemView.findViewById(R.id.recyclerview_clickableText);
        }

        public void bind(final ClickableViewHolderData clickableViewHolderData) {
            clickableTextView.setText(clickableViewHolderData.getClickableText());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickableViewHolderData.getClickableViewHolderBehavior().onClick();
                }
            });
        }
    }

    class ClickableViewHolderData extends ViewHolderData {
        private final String clickableText;
        private final ClickableViewHolderBehavior clickableViewHolderBehavior;

        ClickableViewHolderData(String clickableText, ClickableViewHolderBehavior clickableViewHolderBehavior) {
            super(CLICKABLE_VIEW_TYPE);
            this.clickableText = clickableText;
            this.clickableViewHolderBehavior = clickableViewHolderBehavior;
        }

        public String getClickableText() {
            return clickableText;
        }

        public ClickableViewHolderBehavior getClickableViewHolderBehavior() {
            return clickableViewHolderBehavior;
        }
    }

    interface ClickableViewHolderBehavior {
        void onClick();
    }

    // DIVIDER ----------------------------------------------------------------------------------------------

    class DividerViewHolder extends RecyclerView.ViewHolder {
        public DividerViewHolder(View itemView) {
            super(itemView);
        }
    }

    class DividerViewHolderData extends ViewHolderData {
        DividerViewHolderData() {
            super(DIVIDER_VIEW_TYPE);
        }
    }

    // EDIT TEXT VIEW ----------------------------------------------------------------------------------------------

    class EditTextViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final EditText editText;

        public EditTextViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recyclerview_text);
            editText = itemView.findViewById(R.id.recyclerview_edittext);
        }

        public void bind(final EditTextViewHolderData editTextViewHolderData) {
            textView.setText(editTextViewHolderData.getQuestionText());
            editText.setHint(editTextViewHolderData.getHintText());
            editText.setInputType(editTextViewHolderData.getInputType());

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    editTextViewHolderData.setUserInput(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    class EditTextViewHolderData extends ViewHolderData {
        private final String questionText;
        private final String hintText;
        private final int inputType;

        private String userInput = "";

        EditTextViewHolderData(String questionText, String hintText, int inputType) {
            super(EDIT_TEXT_VIEW_TYPE);
            this.questionText = questionText;
            this.hintText = hintText;
            this.inputType = inputType;
        }

        public String getQuestionText() {
            return questionText;
        }

        public String getHintText() {
            return hintText;
        }

        public int getInputType() {
            return inputType;
        }

        public String getUserInput() {
            return userInput;
        }

        public void setUserInput(String userInput) {
            this.userInput = userInput;
        }
    }

    // RADIO GROUP ----------------------------------------------------------------------------------------------

    class RadioGroupViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final RadioGroup radioGroup;

        public RadioGroupViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recyclerview_text);
            radioGroup = itemView.findViewById(R.id.recyclerview_radioGroup);
        }

        public void bind(final RadioGroupViewHolderData radioGroupViewHolderData) {
            textView.setText(radioGroupViewHolderData.getQuestionText());

            int orientationType;
            if (radioGroupViewHolderData.isVertical()) {
                orientationType = RadioGroup.VERTICAL;
            } else {
                orientationType = RadioGroup.HORIZONTAL;
            }

            radioGroup.setOrientation(orientationType);
            for (RadioGroupListItem radioGroupListItem : radioGroupViewHolderData.getDescriptionList()) {
                RadioButton radioButton = new RadioButton(context);
                radioButton.setText(radioGroupListItem.getDescription());
                radioButton.setId(radioGroupListItem.getId());
                radioGroup.addView(radioButton);
            }

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    for (int i = 0; i < radioGroupViewHolderData.getDescriptionList().size(); i++) {
                        RadioGroupListItem radioGroupListItem = radioGroupViewHolderData.getDescriptionList().get(i);
                        if (radioGroupListItem.getId() == checkedId) {
                            radioGroupViewHolderData.setCheckedIndex(i);
                        }
                    }
                }
            });
        }
    }

    class RadioGroupViewHolderData extends ViewHolderData {
        private final String questionText;
        private final boolean isVertical;
        private final List<RadioGroupListItem> descriptionList;
        private int checkedIndex;

        RadioGroupViewHolderData(String questionText, boolean isVertical, List<RadioGroupListItem> descriptionList) {
            super(RADIO_GROUP_VIEW_TYPE);
            this.questionText = questionText;
            this.isVertical = isVertical;
            this.descriptionList = descriptionList;
        }

        public String getQuestionText() {
            return questionText;
        }

        public boolean isVertical() {
            return isVertical;
        }

        public List<RadioGroupListItem> getDescriptionList() {
            return descriptionList;
        }

        public int getCheckedIndex() {
            return checkedIndex;
        }

        public void setCheckedIndex(int checkedIndex) {
            this.checkedIndex = checkedIndex;
        }
    }

    class RadioGroupListItem {
        private final String description;
        private boolean isChecked;
        private final int id;

        public RadioGroupListItem(String description, boolean isChecked, int id) {
            this.description = description;
            this.isChecked = isChecked;
            this.id = id;
        }

        public String getDescription() {
            return description;
        }

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public int getId() {
            return id;
        }
    }

    // SPINNER ----------------------------------------------------------------------------------------------

    class SpinnerViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final Spinner spinner;

        public SpinnerViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recyclerview_text);
            spinner = itemView.findViewById(R.id.recyclerview_spinner);
        }

        public void bind(final SpinnerViewHolderData spinnerViewHolderData) {
            textView.setText(spinnerViewHolderData.getQuestionText());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, spinnerViewHolderData.optionsList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    spinnerViewHolderData.selectedItem = spinner.getSelectedItem().toString();
                }
            });
        }
    }

    class SpinnerViewHolderData extends ViewHolderData {
        private final String questionText;
        private final List<String> optionsList;
        private String selectedItem;

        SpinnerViewHolderData(String questionText, List<String> optionsList) {
            super(SPINNER_VIEW_TYPE);
            this.questionText = questionText;
            this.optionsList = optionsList;
        }

        public String getQuestionText() {
            return questionText;
        }

        public List<String> getOptionsList() {
            return optionsList;
        }

        public String getSelectedItem() {
            return selectedItem;
        }

        public void setSelectedItem(String selectedItem) {
            this.selectedItem = selectedItem;
        }
    }
}
