package com.ghanem.ticketapi.constants;

import java.util.ArrayList;
import java.util.List;

public interface TicketConstants {
     static final String AUTH_TOKEN_HEADER_NAME = "Authorization";
     static final String AUTH_TOKEN = "YS1paMp2CVeFxb5f8q1STdiShRABVEVK31Zh";
     static final String POST = "POST";
     static final String GET = "GET";
     public static final String[] EXCLUDE_POST_URLS = new String[] {
             "/users"
     };
     public static final String[] EXCLUDE_GET_URLS = new String[] {
             "/auth",
             "/events"
     };

}
