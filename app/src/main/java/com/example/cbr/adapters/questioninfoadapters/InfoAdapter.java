package com.example.cbr.adapters.questioninfoadapters;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cbr.R;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.CheckBoxViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.CheckBoxWithDescriptionViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.ClickableViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.DoubleTextViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.EditTextViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.HeaderViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.RadioGroupViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.RecordPhotoViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.SingleTextViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.SpinnerViewContainer;
import com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.UnchangeableEditTextViewContainer;
import com.example.cbr.util.StringsUtil;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer.CHECK_BOX_VIEW_TYPE;
import static com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer.CHECK_BOX_WITH_DESCRIPTION_VIEW_TYPE;
import static com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer.CLICKABLE_VIEW_TYPE;
import static com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer.DIVIDER_VIEW_TYPE;
import static com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer.DOUBLE_TEXT_VIEW_TYPE;
import static com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer.EDIT_TEXT_VIEW_TYPE;
import static com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer.HEADER_VIEW_TYPE;
import static com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer.PHOTO_VIEW_TYPE;
import static com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer.RADIO_GROUP_VIEW_TYPE;
import static com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer.SINGLE_TEXT_VIEW_TYPE;
import static com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer.SPINNER_VIEW_TYPE;
import static com.example.cbr.adapters.questioninfoadapters.questiondatacontainers.QuestionDataContainer.UNCHANGEABLE_EDIT_TEXT_VIEW_TYPE;
import static com.example.cbr.util.Constants.CAMERA_REQUEST_CODE;

/**
 * Base class for adapters that display information, currently supports headers, dividers, and two text fields
 * Subclasses should implement generateList() by calling addTextViewHolder(), addDivider(), etc in order of appearance in generateList()
 */
public class InfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    Fragment fragment;
    LayoutInflater layoutInflater;
    private final List<QuestionDataContainer> questionDataContainerList;
    private RecordPhotoViewHolder activeCameraViewHolder = null;

    public InfoAdapter(Fragment fragment, List<QuestionDataContainer> questionDataContainerList) {
        this.context = fragment.getContext();
        this.fragment = fragment;
        this.layoutInflater = LayoutInflater.from(context);
        this.questionDataContainerList = questionDataContainerList;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap bitmap = null;
        try {
            bitmap = MediaStore.Images.Media.getBitmap(fragment.getActivity().getContentResolver(), activeCameraViewHolder.getUri());
        } catch (IOException e) {
            e.printStackTrace();
        }
        activeCameraViewHolder.storePhoto(bitmap);
    }

    public static Bitmap rotateImage(Bitmap bitmap, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType) {
            case SINGLE_TEXT_VIEW_TYPE:
                return new SingleTextViewHolder(layoutInflater.inflate(R.layout.recyclerview_singletext, parent, false));
            case DOUBLE_TEXT_VIEW_TYPE:
                return new DoubleTextViewHolder(layoutInflater.inflate(R.layout.recyclerview_doubletext, parent, false));
            case HEADER_VIEW_TYPE:
                return new HeaderViewHolder(layoutInflater.inflate(R.layout.recyclerview_header, parent, false));
            case CLICKABLE_VIEW_TYPE:
                return new ClickableViewHolder(layoutInflater.inflate(R.layout.recyclerview_clickable, parent, false));
            case DIVIDER_VIEW_TYPE:
                return new DividerViewHolder(layoutInflater.inflate(R.layout.recyclerview_divider, parent, false));
            case EDIT_TEXT_VIEW_TYPE:
                return new EditTextViewHolder(layoutInflater.inflate(R.layout.recyclerview_edittext, parent, false));
            case RADIO_GROUP_VIEW_TYPE:
                return new RadioGroupViewHolder(layoutInflater.inflate(R.layout.recyclerview_radiogroup, parent, false));
            case SPINNER_VIEW_TYPE:
                return new SpinnerViewHolder(layoutInflater.inflate(R.layout.recyclerview_spinner, parent, false));
            case CHECK_BOX_VIEW_TYPE:
                return new CheckBoxViewHolder(layoutInflater.inflate(R.layout.recyclerview_checkbox, parent, false));
            case CHECK_BOX_WITH_DESCRIPTION_VIEW_TYPE:
                return new CheckBoxWithDescriptionViewHolder(layoutInflater.inflate(R.layout.recyclerview_checkbox_with_description, parent, false));
            case UNCHANGEABLE_EDIT_TEXT_VIEW_TYPE:
                return new UnchangeableEditTextViewHolder(layoutInflater.inflate(R.layout.recyclerview_unchangeable_edit_text, parent, false));
            case PHOTO_VIEW_TYPE:
                return new RecordPhotoViewHolder(layoutInflater.inflate(R.layout.recyclerview_photo, parent, false));
        }
        return new SingleTextViewHolder(layoutInflater.inflate(R.layout.recyclerview_doubletext, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case SINGLE_TEXT_VIEW_TYPE:
                ((SingleTextViewHolder) holder).bind((SingleTextViewContainer) questionDataContainerList.get(position));
                break;
            case DOUBLE_TEXT_VIEW_TYPE:
                ((DoubleTextViewHolder) holder).bind((DoubleTextViewContainer) questionDataContainerList.get(position));
                break;
            case HEADER_VIEW_TYPE:
                ((HeaderViewHolder) holder).bind((HeaderViewContainer) questionDataContainerList.get(position));
                break;
            case CLICKABLE_VIEW_TYPE:
                ((ClickableViewHolder) holder).bind((ClickableViewContainer) questionDataContainerList.get(position));
            case DIVIDER_VIEW_TYPE:
                break;
            case EDIT_TEXT_VIEW_TYPE:
                ((EditTextViewHolder) holder).bind((EditTextViewContainer) questionDataContainerList.get(position));
                break;
            case RADIO_GROUP_VIEW_TYPE:
                ((RadioGroupViewHolder) holder).bind((RadioGroupViewContainer) questionDataContainerList.get(position));
                break;
            case SPINNER_VIEW_TYPE:
                ((SpinnerViewHolder) holder).bind((SpinnerViewContainer) questionDataContainerList.get(position));
                break;
            case CHECK_BOX_VIEW_TYPE:
                ((CheckBoxViewHolder) holder).bind((CheckBoxViewContainer) questionDataContainerList.get(position));
                break;
            case CHECK_BOX_WITH_DESCRIPTION_VIEW_TYPE:
                ((CheckBoxWithDescriptionViewHolder) holder).bind((CheckBoxWithDescriptionViewContainer) questionDataContainerList.get(position));
                break;
            case UNCHANGEABLE_EDIT_TEXT_VIEW_TYPE:
                ((UnchangeableEditTextViewHolder) holder).bind((UnchangeableEditTextViewContainer) questionDataContainerList.get(position));
            break;
            case PHOTO_VIEW_TYPE:
                ((RecordPhotoViewHolder) holder).bind(((RecordPhotoViewContainer) questionDataContainerList.get(position)));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return questionDataContainerList.get(position).getViewType();
    }

    @Override
    public int getItemCount() {
        return questionDataContainerList.size();
    }

    private void onDataChanged(int positionChanged, QuestionDataContainer questionDataContainer) {
        EventBus eventBus = EventBus.getDefault();
        eventBus.post(new DataChangedEvent(positionChanged, questionDataContainer));
    }

    private class SingleTextViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;

        public SingleTextViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recyclerview_text);
        }

        public void bind(SingleTextViewContainer singleTextViewHolderData) {
            textView.setText(singleTextViewHolderData.getText());
            textView.setTextSize(singleTextViewHolderData.getTextSizeSp());
        }
    }

    private class DoubleTextViewHolder extends RecyclerView.ViewHolder {
        private final TextView firstTextView;
        private final TextView secondTextView;

        public DoubleTextViewHolder(View itemView) {
            super(itemView);
            firstTextView = itemView.findViewById(R.id.recyclerview_firstText);
            secondTextView = itemView.findViewById(R.id.recyclerview_edittext);
        }

        public void bind(DoubleTextViewContainer doubleTextViewHolderData) {
            firstTextView.setText(doubleTextViewHolderData.getFirstText());

            if (doubleTextViewHolderData.getSecondText() == null || doubleTextViewHolderData.getSecondText().isEmpty()) {
                secondTextView.setText(context.getString(R.string.na));
            } else {
                secondTextView.setText(doubleTextViewHolderData.getSecondText());
            }
        }
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {
        private final TextView headerTextView;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            headerTextView = itemView.findViewById(R.id.recyclerview_headerText);
        }

        public void bind(HeaderViewContainer headerViewHolderData) {
            headerTextView.setText(headerViewHolderData.getHeaderText());
        }
    }

    private class ClickableViewHolder extends RecyclerView.ViewHolder {
        private final TextView clickableTextView;

        public ClickableViewHolder(@NonNull View itemView) {
            super(itemView);
            clickableTextView = itemView.findViewById(R.id.recyclerview_clickableText);
        }

        public void bind(final ClickableViewContainer clickableViewHolderData) {
            clickableTextView.setText(clickableViewHolderData.getClickableText());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickableViewHolderData.getClickableViewHolderBehavior().onClick();
                }
            });
        }
    }

    private class DividerViewHolder extends RecyclerView.ViewHolder {
        public DividerViewHolder(View itemView) {
            super(itemView);
        }
    }

    private class EditTextViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final EditText editText;

        public EditTextViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recyclerview_text);
            editText = itemView.findViewById(R.id.recyclerview_edittext);
        }

        public void bind(final EditTextViewContainer editTextViewHolderData) {
            textView.setText(editTextViewHolderData.getQuestionText());
            textView.setTextSize(editTextViewHolderData.getQuestionTextSizeSp());
            editText.setHint(editTextViewHolderData.getHintText());
            editText.setInputType(editTextViewHolderData.getInputType());

            if (editText.getText().toString().isEmpty()) {
                editText.setHint(editTextViewHolderData.getHintText());
                editText.setText(editTextViewHolderData.getInitialText());
            }

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    editTextViewHolderData.setUserInput(s.toString());
                    onDataChanged(getLayoutPosition(), editTextViewHolderData);

                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    private class RadioGroupViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final RadioGroup radioGroup;

        public RadioGroupViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recyclerview_text);
            radioGroup = itemView.findViewById(R.id.recyclerview_radioGroup);
        }

        public void bind(final RadioGroupViewContainer radioGroupViewHolderData) {
            textView.setText(radioGroupViewHolderData.getQuestionText());

            int orientationType;
            if (radioGroupViewHolderData.isVertical()) {
                orientationType = RadioGroup.VERTICAL;
            } else {
                orientationType = RadioGroup.HORIZONTAL;
            }

            if (radioGroup.getChildCount() == 0) {
                radioGroup.setOrientation(orientationType);
                for (RadioGroupViewContainer.RadioGroupListItem radioGroupListItem : radioGroupViewHolderData.getDescriptionList()) {
                    RadioButton radioButton = new RadioButton(context);
                    radioButton.setText(radioGroupListItem.getDescription());
                    radioButton.setId(radioGroupListItem.getId());
                    radioGroup.addView(radioButton);
                }

                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        for (int i = 0; i < radioGroupViewHolderData.getDescriptionList().size(); i++) {
                            RadioGroupViewContainer.RadioGroupListItem radioGroupListItem = radioGroupViewHolderData.getDescriptionList().get(i);
                            if (radioGroupListItem.getId() == checkedId) {
                                radioGroupViewHolderData.setCheckedIndex(i);
                            }
                        }
                        onDataChanged(getLayoutPosition(), radioGroupViewHolderData);
                    }
                });
            }
        }
    }

    private class SpinnerViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final Spinner spinner;

        public SpinnerViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recyclerview_text);
            spinner = itemView.findViewById(R.id.recyclerview_spinner);
        }

        public void bind(final SpinnerViewContainer spinnerViewHolderData) {
            textView.setText(spinnerViewHolderData.getQuestionText());
            textView.setTextSize(spinnerViewHolderData.getQuestionTextSizeSp());
            ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, spinnerViewHolderData.getOptionsList());
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);

            for (int i = 0; i < spinnerViewHolderData.getOptionsList().size(); i++) {
                if (spinnerViewHolderData.getOptionsList().get(i).equals(spinnerViewHolderData.getSelectedItem())) {
                    spinner.setSelection(i);
                }
            }

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    spinnerViewHolderData.setSelectedItem(spinner.getSelectedItem().toString());
                    onDataChanged(getLayoutPosition(), spinnerViewHolderData);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    private class CheckBoxViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox checkBox;

        public CheckBoxViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.recyclerview_checkBox);
        }

        public void bind(final CheckBoxViewContainer checkBoxViewContainer) {
            checkBox.setText(checkBoxViewContainer.getQuestionText());
            checkBox.setChecked(checkBoxViewContainer.isChecked());

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    checkBoxViewContainer.setChecked(isChecked);
                    onDataChanged(getLayoutPosition(), checkBoxViewContainer);
                }
            });
        }
    }

    private class CheckBoxWithDescriptionViewHolder extends RecyclerView.ViewHolder {
        private final CheckBox checkBox;
        private final EditText editText;

        public CheckBoxWithDescriptionViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.recyclerview_checkBox_with_description);
            editText = itemView.findViewById(R.id.recyclerview_editText);
        }

        public void bind(final CheckBoxWithDescriptionViewContainer viewContainer) {
            checkBox.setText(viewContainer.getCheckBoxText());
            editText.setHint(viewContainer.getEditTextHint());
            editText.setInputType(viewContainer.getInputType());

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    viewContainer.setChecked(isChecked);
                    if (isChecked) {
                        editText.setVisibility(View.VISIBLE);
                    } else {
                        editText.setVisibility(View.GONE);
                    }
                    onDataChanged(getLayoutPosition(), viewContainer);
                }
            });
            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    viewContainer.setUserInput(s.toString());
                    onDataChanged(getLayoutPosition(), viewContainer);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }
    }

    private class UnchangeableEditTextViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final EditText editText;

        public UnchangeableEditTextViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.recyclerview_dateQuestionTextView);
            editText = itemView.findViewById(R.id.recyclerview_dateEditText);
        }

        public void bind(final UnchangeableEditTextViewContainer viewContainer) {
            textView.setText(viewContainer.getQuestionText());
            textView.setTextSize(viewContainer.getQuestionTextSizeSp());
            editText.setText(viewContainer.getTextEditText());
        }
    }

    private class RecordPhotoViewHolder extends RecyclerView.ViewHolder {
        private final Button recordButton;
        private final ImageView photo;
        private RecordPhotoViewContainer recordPhotoViewContainer;
        private Uri uri;

        public RecordPhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            recordButton = itemView.findViewById(R.id.recyclerview_cameraButton);
            photo = itemView.findViewById(R.id.recyclerview_photo);
        }

        public void bind(final RecordPhotoViewContainer recordPhotoViewContainer) {
            this.recordPhotoViewContainer = recordPhotoViewContainer;

            if (recordPhotoViewContainer.getImage() != null) {
                photo.setImageBitmap(recordPhotoViewContainer.getImage());
            }

            recordButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activeCameraViewHolder = RecordPhotoViewHolder.this;

                    ContentValues contentValues = new ContentValues();
                    contentValues.put(MediaStore.Images.Media.TITLE, "CBR_picture_" + StringsUtil.dateToUKFormat(new Date()));
                    uri = fragment.getActivity().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    fragment.startActivityForResult(intent, CAMERA_REQUEST_CODE);
                }
            });
        }

        public Uri getUri() {
            return uri;
        }

        public void storePhoto(Bitmap bitmap) {
            recordPhotoViewContainer.setImage(bitmap);
            photo.setImageBitmap(bitmap);
            activeCameraViewHolder = null;
        }
    }

    public class DataChangedEvent {
        int positionChanged;
        QuestionDataContainer questionDataContainer;

        public DataChangedEvent(int positionChanged, QuestionDataContainer questionDataContainer) {
            this.positionChanged = positionChanged;
            this.questionDataContainer = questionDataContainer;
        }

        public int getPositionChanged() {
            return positionChanged;
        }

        public void setPositionChanged(int positionChanged) {
            this.positionChanged = positionChanged;
        }

        public QuestionDataContainer getQuestionDataContainer() {
            return questionDataContainer;
        }

        public void setQuestionDataContainer(QuestionDataContainer questionDataContainer) {
            this.questionDataContainer = questionDataContainer;
        }
    }
}
