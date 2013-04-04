package fr.sii.formation.gwt.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.user.client.ui.Image;

@RemoteServiceRelativePath("pictureService")
public interface PictureRemoteService extends RemoteService
{

	Image getPicture(String id);

}