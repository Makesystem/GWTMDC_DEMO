package gwt.material.design.components.demo.client.app.components.fileUpload;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.events.AbortEvent;
import gwt.material.design.components.client.events.AddEvent;
import gwt.material.design.components.client.events.ChangeEvent;
import gwt.material.design.components.client.events.DoneEvent;
import gwt.material.design.components.client.events.ErrorEvent;
import gwt.material.design.components.client.events.ProgressEvent;
import gwt.material.design.components.client.events.SelectionEvent;
import gwt.material.design.components.client.events.StartEvent;
import gwt.material.design.components.client.events.StopEvent;
import gwt.material.design.components.client.ui.MaterialFileUpload;
import gwt.material.design.components.client.ui.MaterialList;
import gwt.material.design.components.client.ui.MaterialSnackbar;
import gwt.material.design.components.client.ui.MaterialTextField;
import gwt.material.design.components.client.ui.MaterialFileUpload.UploadRequest;
import gwt.material.design.components.client.utils.debug.Console;
import gwt.material.design.components.client.utils.helper.ObjectHelper;
import gwt.material.design.components.demo.client.app.components.fileUpload.misc.FileWidget;

public class FileUploadDemo extends Composite {

	private static ColorChooserUiBinder uiBinder = GWT.create(ColorChooserUiBinder.class);

	interface ColorChooserUiBinder extends UiBinder<Widget, FileUploadDemo> {
	}

	@UiField
	MaterialFileUpload to_do_tests_element;

	@UiField
	MaterialTextField accept_field;
	@UiField
	MaterialTextField max_number_of_files_field;
	@UiField
	MaterialTextField max_file_size_field;
	@UiField
	MaterialTextField limit_multi_file_upload_size_field;

	@UiField
	MaterialList files_content;

	public FileUploadDemo() {
		initWidget(uiBinder.createAndBindUi(this));
		to_do_tests_element.addFormData("Property 1", "Value 1");
		to_do_tests_element.addFormData("Property 2", "Value 2");
		to_do_tests_element.addFormData("Property 3", "Value 3");
	}

	@UiHandler("to_do_tests_element")
	void onChange(final ChangeEvent<Collection<MaterialFileUpload.File>> event) {
		Console.log("on change: " + event.getValue().size());
	}

	@UiHandler("to_do_tests_element")
	void onStart(final StartEvent event) {
		Console.log("on start");
	}

	@UiHandler("to_do_tests_element")
	void onStop(final StopEvent event) {
		Console.log("on stop");
	}

	private Map<MaterialFileUpload.File, FileWidget> files = new LinkedHashMap<>();

	@UiHandler("to_do_tests_element")
	void onAdd(final AddEvent<Collection<MaterialFileUpload.File>> event) {
		Console.log("on add");

		if (to_do_tests_element.isSingleFileUploads())
			removeFiles(files.keySet());

		event.getValue().forEach(file -> {
			final FileWidget fileWidget = new FileWidget(to_do_tests_element, file);
			files_content.add(fileWidget);
			files.put(file, fileWidget);
		});
	}

	@UiHandler("to_do_tests_element")
	void onDone(final DoneEvent<MaterialFileUpload.Data> event) {
		Console.log("on done: " + event.getValue().getResponse());
		// removeFiles(event.getValue().getFiles());
	}

	@UiHandler("to_do_tests_element")
	void onProgress(final ProgressEvent<Collection<MaterialFileUpload.File>> event) {
		final double progress = ((double) event.getLoaded()) / ((double) event.getTotal());
		Console.log("on progress: " + event.getLoaded() + " / " + event.getTotal() + " = " + (progress * 100d) + "%");

		if (event.getData().size() == 1)
			files.get(event.getData().iterator().next()).setProgress(progress);

	}

	@UiHandler("to_do_tests_element")
	void onError(final ErrorEvent<MaterialFileUpload.Data> event) {
		Console.log("on error: " + event.getDescription());

		final MaterialSnackbar snackbar = new MaterialSnackbar();
		snackbar.setTimeout(5000);
		snackbar.setBackgroundColor(Color.MDC_THEME_ERROR);
		snackbar.setText(event.getDescription());
		snackbar.open();
	}

	@UiHandler("to_do_tests_element")
	void onAbort(final AbortEvent<MaterialFileUpload.Data> event) {
		Console.log("on abort: " + event.getValue().getLoaded());
	}

	void removeFiles(final Collection<MaterialFileUpload.File> files) {
		for (MaterialFileUpload.File file : files) {
			to_do_tests_element.removeFile(file);
			this.files.remove(file).removeFromParent();
		}
	}

	// //////////////////////////////////////////////////////////////////////////
	UploadRequest uploadRequest;

	@UiHandler("submit")
	void onSubmit(final ClickEvent event) {
		uploadRequest = to_do_tests_element.submit();
	}

	@UiHandler("abort")
	void onAbort(final ClickEvent event) {
		if (uploadRequest != null)
			uploadRequest.abort();
	}

	@UiHandler("enabled_check")
	void onSelectEnabled(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setEnabled(event.getValue());
	}

	@UiHandler("auto_upload_check")
	void onSelectAutoUpload(final SelectionEvent<Boolean> event) {
		to_do_tests_element.setAutoUpload(event.getValue());
	}

	@UiHandler("single_file_uploads_check")
	void onSelectSingleFileUploads(final SelectionEvent<Boolean> event) {
		max_number_of_files_field.setEnabled(!event.getValue());
		to_do_tests_element.setSingleFileUploads(event.getValue());
	}

	@UiHandler("accept_field")
	void onSelecAccept(final KeyUpEvent event) {
		to_do_tests_element.setAccept(accept_field.getValue());
	}

	@UiHandler("max_file_size_field")
	void onSelecMaxFileSize(final KeyUpEvent event) {
		to_do_tests_element.setMaxFileSize(ObjectHelper.toInteger(max_file_size_field.getValue()));
	}

	@UiHandler("max_number_of_files_field")
	void onSelecMaxNumberOfFiles(final KeyUpEvent event) {
		to_do_tests_element.setMaxNumberOfFiles(ObjectHelper.toInteger(max_number_of_files_field.getValue()));
	}

	@UiHandler("limit_multi_file_upload_size_field")
	void onSelecLimitMultiFileUploadSize(final KeyUpEvent event) {
		to_do_tests_element.setLimitMultiFileUploadSize(ObjectHelper.toInteger(limit_multi_file_upload_size_field.getValue()));
	}
}
