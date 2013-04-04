package fr.sii.formation.gwt.server.remoteservice;

import com.google.gwt.rpc.client.impl.RemoteException;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.sii.formation.gwt.client.service.PictureRemoteService;

public class PictureRemoteServiceImpl extends RemoteServiceServlet implements PictureRemoteService
{

	private static final long serialVersionUID = 1L;

	@Override
	public Image getPicture(String id) throws RemoteException
	{
		// TODO Auto-generated method stub
		return null;
	}

}
