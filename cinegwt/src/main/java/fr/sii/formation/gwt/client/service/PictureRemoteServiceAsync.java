package fr.sii.formation.gwt.client.service;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public interface PictureRemoteServiceAsync
{

    /**
     * GWT-RPC service  asynchronous (client-side) interface
     * @see fr.sii.formation.gwt.client.service.PictureRemoteService
     */
    void getPicture( java.lang.String id, AsyncCallback<com.google.gwt.user.client.ui.Image> callback );


    /**
     * Utility class to get the RPC Async interface from client-side code
     */
    public static final class Util 
    { 
        private static PictureRemoteServiceAsync instance;

        public static final PictureRemoteServiceAsync getInstance()
        {
            if ( instance == null )
            {
                instance = (PictureRemoteServiceAsync) GWT.create( PictureRemoteService.class );
                ServiceDefTarget target = (ServiceDefTarget) instance;
                target.setServiceEntryPoint( GWT.getModuleBaseURL() + "pictureService" );
            }
            return instance;
        }

        private Util()
        {
            // Utility class should not be instanciated
        }
    }
}
