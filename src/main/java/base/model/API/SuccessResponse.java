package base.model.API;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class SuccessResponse {

    public Buyer buyer;

    @Data
    public class Plaid{
        private Object public_token;
        private Object status;
    }
    @Data
    public class Buyer{
        private int id;
        private String uid;
        private String email;
        private String provider;
        private String first_name;
        private String last_name;
        private String contact_number;
        private String image;
        private boolean sub_buyer;
        private boolean invoice_only;
        private String stripe_customer_id;
        private boolean did_agree_to_terms;
        private int store_id;
        private String store_name;
        private int buyer_company_id;
        private String buyer_company_name;
        private boolean buyer_company_approved;
        private Object buyer_company_onboarding_step;
        private ArrayList<Integer> buyer_email_group_ids;
        private ArrayList<Object> uneditable_email_group_ids;
        private Plaid plaid;
    }
}
