package gwt.material.design.components.demo.client.app.components.fileUpload.misc;

import com.google.gwt.event.shared.HandlerRegistration;

import gwt.material.design.components.client.constants.Color;
import gwt.material.design.components.client.constants.IconType;
import gwt.material.design.components.client.ui.MaterialFabProgress;
import gwt.material.design.components.client.ui.MaterialFileUpload;
import gwt.material.design.components.client.ui.MaterialIconButton;
import gwt.material.design.components.client.ui.MaterialListItem;
import gwt.material.design.components.client.utils.helper.Formatation;

public class FileWidget extends MaterialListItem {

	private final MaterialFileUpload.File file;
	private final MaterialFileUpload fileUpload;
	private final MaterialFabProgress send = new MaterialFabProgress();
	private final MaterialIconButton delete = new MaterialIconButton(IconType.DELETE);

	private MaterialFileUpload.UploadRequest uploadRequest;
	private HandlerRegistration handler;

	public FileWidget(final MaterialFileUpload fileUpload, final MaterialFileUpload.File file) {
		super();
		this.fileUpload = fileUpload;
		this.file = file;
	}

	@Override
	protected void onLoad() {
		super.onLoad();

		setText(file.getName());
		setTextSecondary(Formatation.bytes(file.getSize()) + " | " + file.getType());
		setPadding(4);

		delete.addClickHandler(event -> {
			fileUpload.removeFile(file);
			removeFromParent();
		});

		send.setIcon(IconType.CLOUD_UPLOAD);
		send.setMargin(8);
		handler = send.addClickHandler(event -> {

			if (uploadRequest == null) {
				send.setIcon(IconType.CANCEL);

				uploadRequest = fileUpload.submit(new MaterialFileUpload.File[] { file });
				uploadRequest.onDone((result) -> {
					uploadRequest = null;
					send.setIcon(IconType.DONE, true);
					send.setIconColor(Color.MDC_THEME_SUCCESS);
					send.setIndicatorColor(Color.MDC_THEME_SUCCESS);
					handler.removeHandler();
				});
				uploadRequest.onError((error) -> {
					uploadRequest = null;
					send.setIcon(IconType.CLOUD_UPLOAD, true);
					send.setProgress(0);
				});
				uploadRequest.onAbort(() -> {
					uploadRequest = null;
					send.setIcon(IconType.CLOUD_UPLOAD, true);
					send.setProgress(0);
				});
			} else {
				uploadRequest.abort();
				uploadRequest = null;
			}
		});

		addEndDetail(send, true);
		addEndDetail(delete, true);

		switch (file.getType()) {
		case IMAGE:
			setAvatarUrl(file.getDataAsUrl());			
			break;
		default:
			setIcon(IconType.INSERT_DRIVE_FILE);
			break;
		}
		
	}

	public void setProgress(final double progress) {
		send.setProgress(progress, true);
	}

	public MaterialFileUpload.File getFile() {
		return file;
	}

}
