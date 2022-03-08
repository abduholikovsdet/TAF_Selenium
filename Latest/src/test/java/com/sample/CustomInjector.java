package com.sample;


import com.cme.pagemethods.*;
import com.fcbt.taf.core.BaseCustomInjector;

public class CustomInjector extends BaseCustomInjector {

    public CustomInjector()
    {
        //Page Classes
        addClass(DashboardPageMethods.class);
        addClass(CustomerSearchPageMethods.class);
        addClass(EntityInfoPageMethods.class);
        addClass(DealInfoPageMethods.class);
        addClass(FacilityPageMethods.class);
        addClass(CollateralInfoPageMethods.class);
        addClass(FeesPageMethods.class);
        addClass(DDTPageMethods.class);
        addClass(RoutePageMethods.class);
        addClass(BorrowerRiskTypesMethods.class);
        addClass(YBSReportingMethods.class);
        addClass(LiquidCreditMethods.class);
        addClass(RelatedDocsMethods.class);
        addClass(GuidelineCompliancePageMethods.class);
        addClass(CovenantPageMethods.class);
        addClass(AuthorizationPageMethods.class);
        addClass(DocumentsPageMethods.class);
        addClass(RelatedEntityInfoPageMethods.class);
        addClass(DisbursementsPageMethods.class);
        addClass(RelatedDocsMethods.class);
        addClass(OBSPageMethods.class);
        addClass(EntityBoardingPageMethods.class);
        addClass(DealBookingPageMethods.class);
    }

}
