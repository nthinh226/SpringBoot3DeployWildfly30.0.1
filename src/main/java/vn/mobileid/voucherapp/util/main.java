package vn.mobileid.voucherapp.util;

import io.jsonwebtoken.lang.Strings;

import java.util.Base64;

public class main {
    public static void main(String[] args) {
//        String authorizationHeader = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJPMDcxTHRUNktSVWJnYWwzcVhtWml5TUZOWVNuN19GbTNfZEV1emYySkNvIn0.eyJleHAiOjE3MTM3ODI1MTgsImlhdCI6MTcxMzc4MDcxOCwianRpIjoiYWQxYmVjNjktOWJkZC00ODE2LThkMjgtNjZiNGUzNzcyNDg1IiwiaXNzIjoiaHR0cDovLzE5Mi4xNjguMy41OTo4MDgwL2F1dGgvcmVhbG1zL21hc3RlciIsImF1ZCI6ImFjY291bnQiLCJzdWIiOiI4YmM0YzcwNC0wOTRlLTRkMjItYmRjMi1kMDhlYTQyOTMxMmQiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiI4ZjM5NGM2Yy05M2YzLTQ0NWMtYWYyYi03YmFhNTI2NjhhZTMiLCJzZXNzaW9uX3N0YXRlIjoiNDdjNWU1NzktYWRkZi00OWFhLWIwZjgtNDJlOGI4NWViNWE3IiwiYWNyIjoiMSIsInJlYWxtX2FjY2VzcyI6eyJyb2xlcyI6WyJkZWZhdWx0LXJvbGVzLW1hc3RlciIsIm9mZmxpbmVfYWNjZXNzIiwidW1hX2F1dGhvcml6YXRpb24iXX0sInJlc291cmNlX2FjY2VzcyI6eyJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6ImVtYWlsIHByb2ZpbGUiLCJzaWQiOiI0N2M1ZTU3OS1hZGRmLTQ5YWEtYjBmOC00MmU4Yjg1ZWI1YTciLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJUaGluaCBUcmFuIiwicHJlZmVycmVkX3VzZXJuYW1lIjoidGhpbmh0biIsImdpdmVuX25hbWUiOiJUaGluaCIsImZhbWlseV9uYW1lIjoiVHJhbiIsImVtYWlsIjoidGhpbmh0bkBtb2JpbGUtaWQudm4ifQ.BRquaq0vDpat3S7MWvx67WiYMJIolZnP730v6VtHfbBPRZsyY1U0ofrBBQ8haTkrpKmcFMmBtUuI3f29iZcIXdgSEL_JLs2tF2EgXqddd0wr7BqHhc9-QnERiVUQZd1F36shlcPvZRSH1ulZ7ONFTmaYh0bViGwKyurlSpEKuI_rH5djqkk5iiSQv8_Y6R_2qmYLrIkMynii7POVPJ2cJvaQkXl6n8trltCva26JSs6btYEXHIGN9s5Kg_Bzebqt_2V5U4QJUeiX8SYqRoMUW6ZYesP0974d_R0QodMY1Fbkdkctk8NK3klO1RpX4CUfF4OBCPCzfgFbEhWT_h5Pjw";
//        String value = Strings.replace(authorizationHeader,"Bearer ","");
//        System.out.println(JWTDecoderUtil.getPayloadJWT(value));

        System.out.println(Base64.getEncoder().encodeToString("admin:admin123".getBytes()));
    }
}
