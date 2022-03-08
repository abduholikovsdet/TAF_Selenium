package com.cme.runner;

import com.cme.pages.DDTPage;
import com.cme.pages.EntityBoardingPage;
import com.fcbt.taf.core.reporting.Reporter;
import org.apache.commons.lang.WordUtils;
import org.apache.commons.net.ntp.TimeStamp;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


public class PocMainClass {


    public static void main(String[] args) {
        //  Query1.java:  Query an mSQL database using JDBC.

/**
 * A JDBC SELECT (JDBC query) example program.
 */

//
//        HashMap<String, String> conversionData = new HashMap<>();
//        String bankNumber = "006";
//        String EnvName = "FBLDATO";
//        String EnvDomain = "BE.develop.fcbt";
//        String LnAdm = null;
//
////        String qry	="SELECT ao.officer_num, ao.officer_fname, ao.officer_lname "
////                .concat("FROM [FBCME].[dbo].[t_ccs_admin_officer]  ao ")
////                .concat("join [FBCME].[dbo].[t_ccs_admin_legal_ent] le on le.lgl_ent_id = ao.officer_affil_id ")
//////                .concat("join [FBLoanIQ].[LS2USER].[VLS_USER_PROFILE] lup on lup.UPT_NME_USER_FIRST = ao.officer_fname And lup.UPT_NME_USER_LAST = ao.officer_lname ")
//////				.concat("where [officer_num] is not null AND lup.UPT_CDE_JOB_FUNC like '%LO%' and le.lgl_ent_short_name = '" +bankNumber+ "'");
////                .concat("where [officer_num] is not null AND le.lgl_ent_short_name = '" +bankNumber+ "'");
//
//
////        try {
////            String connectionUrl = "jdbc:sqlserver://"+ EnvName + EnvDomain +":1433;databaseName=FBCME;user=SVCCMETESTUSER01;password=This1is2a3sqllogin"; //integratedSecurity=true";
////            Connection con = DriverManager.getConnection(connectionUrl);
////            Statement stmt = con.createStatement();
////            ResultSet rs = stmt.executeQuery(qry);
////            if (rs.next()) {
//////                while (rs.next()) {
////                    System.out.println("Officer: "+ rs.getString("officer_num"));
//////                    LoanOfficer.add(0,rs.getString("officer_num"));
//////                    LoanOfficer.add(1,rs.getString("officer_fname"));
//////                    LoanOfficer.add(2,rs.getString("officer_lname"));
//////                    break;
//////                }
////            }
////
////            con.close();
////
////
////        }
////        // Handle any errors that may have occurred.
////        catch (SQLException e) {
////            e.printStackTrace();
////        }
//
//String q = "SELECT"
//.concat("(SELECT CAST(ENV_TXT_VAR_VALUE AS DATE) FROM LS2USER.VLS_ENVIRONMENT WHERE ENV_NME_VAR_CLASS='ZONE1' AND ENV_NME_VAR_NAME = 'Current Business Date') AS CUR_BUS_DATE")
//.concat(",DEA_NME_ALIAS_NAME,A.DEA_NME_DEAL, A.DEA_PID_DEAL AS LOANIQ_ID,PRIM.CUS_NME_FULL_NAME AS PRIM_BORROWER,CDS.GB1_DSC_CODE AS CUST_CLASSIFICATION,SEC_BORROWERS")
//.concat(",CASE WHEN DEA_CDE_DEAL_STAT IN ('AGENT','CLOSE','NNHST') THEN CONCAT(DEA_CDE_DEAL_STAT,' - CLOSED') ELSE DEA_CDE_DEAL_STAT END AS STATUS")
//.concat(", DEA_TSP_REC_CREATE, DEA_UID_REC_CREATE,DEA_IND_AUTO_GEN,DEA_IND_AUTO_REL,DEA_CDE_BRANCH,DEA_DTE_AGREEMENT,DEA_DTE_DEAL_CLSD")
//.concat(",DEA_AMT_GLO_PRECLO AS DEAL_AMT,NO_OF_PRIORAMENDS,PRIORAMEND_LAST_EFF_DATE")
//.concat(",PRIORAMEND_LAST_TSP,DEA_CDE_ORIG_CCY,DCL.GB2_DSC_CODE AS DEAL_CLASSIFICATION, DEA_CDE_DEPT, A.DEA_CDE_EXPENSE")
//.concat(",PRICING_OPTIONS,DEA_CDE_INPAL_RULE")
//.concat(",CASE WHEN DEF_PID_DEAL IS NOT NULL THEN 'YES' ELSE 'NO' END AS DEA_EVENTFEES_EXISTS")
//.concat(",CASE WHEN FEP_PID_DEAL IS NOT NULL THEN 'YES' ELSE 'NO' END AS DEA_ONG_FEES_EXISTS")
//.concat(",NO_OF_FACILITIES,FAC.FAC_CDE_FAC_TYPE,FAC.FAC_NUM_FAC_CNTL,FAC.FAC_NME_FACILITY,FAC.FAC_DTE_EFFECTIVE,FAC.FAC_DTE_EXPIRY,FAC.FAC_DTE_FINAL_MAT,FAC.FAC_AMT_GLOBL_CURR AS FAC_CURRENT_CMT")
//.concat(",CASE WHEN FAC.FAC_DTE_EXPIRY < (SELECT CAST(ENV_TXT_VAR_VALUE AS DATE) FROM LS2USER.VLS_ENVIRONMENT WHERE ENV_NME_VAR_CLASS='ZONE1' AND ENV_NME_VAR_NAME = 'Current Business Date') THEN 0 ELSE FAC_AMT_GLOBL_CURR - COALESCE(FAC_AMT_OST,0) END AS FAC_AVAIL_CMT")
//.concat(",CASE WHEN FTR_PID_DEAL IS NOT NULL THEN 'YES' ELSE 'NO' END AS FAC_CMT_CHANGED_VIA_AMENDMENT")
//.concat(",PD_DESC,PD_RATING_EFF_DATE,LGD_DESC,LGD_RATING_EFF_DATE")
//.concat(",O1.OEA_TXT_VALUE AS PPP_TYPE,O2.OEA_TXT_VALUE AS PPP_EFF_DATE")
//.concat(",CASE WHEN PGU_PID_PRODUCT_ID IS NOT NULL THEN 'YES' ELSE 'NO' END AS DEAL_GUARANTORS_EXISTS")
//.concat(",CASE WHEN G2_PID_DEAL IS NOT NULL THEN 'YES' ELSE 'NO' END AS FACILITY_GUARANTORS_EXISTS")
//.concat(",CASE WHEN M1.MIC_PID_PRODUCT_ID IS NOT NULL THEN 'YES' ELSE 'NO' END AS DEAL_MIS_EXISTS")
//.concat(",CASE WHEN MIC_PID_DEAL IS NOT NULL THEN 'YES' ELSE 'NO' END AS FAC_MIS_EXISTS")
//.concat(",CASE WHEN A1.OEA_RID_OWNER IS NOT NULL THEN 'YES' ELSE 'NO' END AS DEAL_ADDL_FLD_EXISTS")
//.concat(",CASE WHEN OEA_PID_DEAL IS NOT NULL THEN 'YES' ELSE 'NO' END AS FAC_ADDL_FLD_EXISTS")
//.concat(",CASE WHEN OST.OST_PID_DEAL IS NOT NULL THEN 'YES' ELSE 'NO' END AS DEA_LOANS_EXISTS")
//.concat(",CASE WHEN OST1_PID_DEAL IS NOT NULL THEN 'YES' ELSE 'NO' END AS DEA_ACTIVE_LOANS_EXISTS")
//.concat(",LOAN.OST_NME_ALIAS,LOAN.OST_CDE_PRICE_OPT,OST_CDE_ACR_PERIOD,LOAN.OST_AMT_CURRENT,ORT_PCT_BASE_RATE*100 AS OST_BASE_RATE,ORT_PCT_SPREAD*100 AS OST_SPREAD,(ORT_PCT_BASE_RATE + COALESCE(ORT_PCT_SPREAD,0))*100 AS OST_ALL_IN")
//.concat(",V1.NEW_TOTAL_COF_RTE * 100 AS COF_RATE,V1.NEW_COF_SPRD*100 AS COF_SPREAD")
//.concat(",SCH_CDE_FXDAMT_TYP AS RESYNC_SETTINGS,OST_IND_AC_SCD_BAL")
//.concat(",OST_DTE_EFFECTIVE AS OST_EFF_DATE,OST_DTE_REPRICING")
//.concat(",SCH_TXT_SCH_TYPE,SCH_CDE_BAL_TYPE AS SCHEDULE_TYPE")
//.concat(",SCI_DTE_EFFECTIVE AS EARLIEST_UNPAID_DUE_DATE, SCI_CDE_F_BAL_TYPE AS SCHED_ITEM_TYPE")
//.concat(",COALESCE(OST_DTE_EXPIRY_ENT,FAC_DTE_FINAL_MAT) AS OST_MAT_DATE")
//.concat(",LAST_PMT_DATE AS PROJ_MAT_DATE,LCR_NME_NAME,ORT_CDE_RATE_BASIS")
//.concat(",OFFICIAL.OEA_TXT_VALUE AS OFFICIAL_LOAN_TYPE")
//.concat(",O3.OEA_TXT_VALUE AS COF_SPRD_LCK_EXP_DTE")
//.concat(",O4.OEA_TXT_VALUE AS RATE_LOCK_DTE")
//.concat(",O5.OEA_TXT_VALUE AS ENCOMP_ORIG")
//.concat("FROM")
//.concat("LS2USER.VLS_DEAL A")
//.concat("JOIN LS2USER.VLS_DEAL_BORROWER ON DEA_PID_DEAL = DBR_PID_DEAL ")
//.concat("JOIN LS2USER.VLS_CUSTOMER PRIM ON DBR_CID_CUST_ID = PRIM.CUS_CID_CUST_ID")
//.concat("AND DBR_IND_PRIM_BORR = 'Y'")
//.concat("LEFT JOIN LS2USER.VLS_FAM_GLOBAL1 CDS ON PRIM.CUS_CDE_CUST_DESC = CDS.GB1_CDE_CODE AND CDS.GB1_TID_TABLE_ID = 'CDS'")
//.concat("JOIN LS2USER.VLS_FAM_GLOBAL2 DCL ON DEA_CDE_DEAL_CLASS = DCL.GB2_CDE_CODE AND DCL.GB2_TID_TABLE_ID = 'DCL'")
//.concat("LEFT JOIN (")
//.concat("SELECT ")
//.concat("DBR_PID_DEAL,  ")
//.concat("STUFF((")
//.concat("SELECT ',' + CUS_NME_FULL_NAME")
//.concat("FROM LS2USER.VLS_DEAL_BORROWER U")
//.concat("JOIN LS2USER.VLS_CUSTOMER ON DBR_CID_CUST_ID = CUS_CID_CUST_ID")
//.concat("WHERE")
//.concat("U.DBR_IND_PRIM_BORR <> 'Y' AND ")
//.concat("U.DBR_PID_DEAL = Y.DBR_PID_DEAL")
//.concat("ORDER BY CUS_NME_FULL_NAME")
//.concat("FOR XML PATH('')")
//.concat("),1,1,'') AS SEC_BORROWERS")
//.concat("FROM LS2USER.VLS_DEAL_BORROWER Y")
//.concat("WHERE DBR_IND_PRIM_BORR <> 'Y' ")
//.concat("GROUP BY DBR_PID_DEAL")
//.concat(") SEC_BORRS ON DEA_PID_DEAL = SEC_BORRS.DBR_PID_DEAL")
//.concat("LEFT JOIN (")
//.concat("SELECT ")
//.concat("IPO_PID_DEAL,  ")
//.concat("STUFF((")
//.concat("SELECT ',' + GB2_DSC_CODE")
//.concat("FROM LS2USER.VLS_INT_PRC_OPTION U")
//.concat("JOIN LS2USER.VLS_FAM_GLOBAL2 ON IPO_CDE_OPTION = GB2_CDE_CODE")
//.concat("AND GB2_TID_TABLE_ID = 'PON'")
//.concat("WHERE")
//.concat("U.IPO_PID_DEAL = Y.IPO_PID_DEAL")
//.concat("ORDER BY GB2_DSC_CODE")
//.concat("FOR XML PATH('')")
//.concat("),1,1,'') AS PRICING_OPTIONS")
//.concat("FROM LS2USER.VLS_INT_PRC_OPTION Y")
//.concat("GROUP BY IPO_PID_DEAL) PO ON DEA_PID_DEAL = IPO_PID_DEAL")
//.concat("LEFT JOIN")
//.concat("(")
//.concat("SELECT DISTINCT PGU_PID_PRODUCT_ID")
//.concat("FROM")
//.concat("LS2USER.VLS_PROD_GUARANTEE")
//.concat("WHERE PGU_CDE_PROD_TYPE = 'DEA'")
//.concat(") G1 ON DEA_PID_DEAL = PGU_PID_PRODUCT_ID")
//.concat("LEFT JOIN")
//.concat("(")
//.concat("SELECT DISTINCT FAC_PID_DEAL AS G2_PID_DEAL")
//.concat("FROM")
//.concat("LS2USER.VLS_PROD_GUARANTEE JOIN LS2USER.VLS_FACILITY ON PGU_PID_PRODUCT_ID = FAC_PID_FACILITY")
//.concat("WHERE PGU_CDE_PROD_TYPE = 'FAC'")
//.concat(") G2 ON DEA_PID_DEAL = G2_PID_DEAL")
//.concat("LEFT JOIN (")
//.concat("SELECT DISTINCT FTR_PID_DEAL FROM")
//.concat("LS2USER.VLS_FAC_COMMIT_TRN")
//.concat("JOIN LS2USER.VLS_DEAL_AMENDMENT ON FTR_RID_GROUP_TRN = DAM_RID_AMENDMENT")
//.concat(") CMT ON DEA_PID_DEAL = FTR_PID_DEAL")
//.concat("LEFT JOIN (")
//.concat("SELECT DISTINCT MIC_PID_PRODUCT_ID FROM")
//.concat("LS2USER.VLS_MIS_CODE")
//.concat("WHERE MIC_CDE_PROD_TYPE = 'DEA'")
//.concat(") M1 ON DEA_PID_DEAL = M1.MIC_PID_PRODUCT_ID")
//.concat("LEFT JOIN (")
//.concat("SELECT DISTINCT FAC_PID_DEAL AS MIC_PID_DEAL FROM")
//.concat("LS2USER.VLS_MIS_CODE")
//.concat("JOIN LS2USER.VLS_FACILITY ON MIC_PID_PRODUCT_ID = FAC_PID_FACILITY")
//.concat("WHERE MIC_CDE_PROD_TYPE = 'FAC'")
//.concat(") M2 ON DEA_PID_DEAL = MIC_PID_DEAL")
//.concat("LEFT JOIN (")
//.concat("SELECT DISTINCT OEA_RID_OWNER FROM")
//.concat("LS2USER.VLS_OBJ_EXT_ATR")
//.concat("--JOIN LS2USER.VLS_FACILITY ON MIC_PID_PRODUCT_ID = FAC_PID_FACILITY")
//.concat("WHERE OEA_CDE_OWNER_TYPE = 'DEA'")
//.concat(") A1 ON DEA_PID_DEAL = A1.OEA_RID_OWNER")
//.concat("LEFT JOIN (")
//.concat("SELECT DISTINCT FAC_PID_DEAL AS OEA_PID_DEAL FROM")
//.concat("LS2USER.VLS_OBJ_EXT_ATR")
//.concat("JOIN LS2USER.VLS_FACILITY ON OEA_RID_OWNER = FAC_PID_FACILITY")
//.concat("WHERE OEA_CDE_OWNER_TYPE = 'FAC'")
//.concat(") A2 ON DEA_PID_DEAL = A2.OEA_PID_DEAL")
//.concat("LEFT JOIN (")
//.concat("SELECT DISTINCT OST_PID_DEAL  FROM")
//.concat("LS2USER.VLS_OUTSTANDING")
//.concat("WHERE OST_AMT_ORIGINAL > 0")
//.concat("AND OST_CDE_OUTSTD_TYP = 'LOAN'")
//.concat(") OST ON DEA_PID_DEAL = OST_PID_DEAL")
//.concat("LEFT JOIN (")
//.concat("SELECT DISTINCT OST_PID_DEAL AS OST1_PID_DEAL  FROM")
//.concat("LS2USER.VLS_OUTSTANDING")
//.concat("WHERE OST_AMT_CURRENT > 0")
//.concat("AND OST_CDE_OUTSTD_TYP = 'LOAN'")
//.concat("AND OST_CDE_OB_ST_CTG = 'ACTUA'")
//.concat(") OST1 ON DEA_PID_DEAL = OST1_PID_DEAL")
//.concat("LEFT JOIN (")
//.concat("SELECT DISTINCT DEF_PID_DEAL  FROM")
//.concat("LS2USER.VLS_DEAL_EVENT_FEE")
//.concat(") DEF ON DEA_PID_DEAL = DEF_PID_DEAL")
//.concat("LEFT JOIN (")
//.concat("SELECT DISTINCT FEP_PID_DEAL  FROM")
//.concat("LS2USER.VLS_FEE_PRC_RULE")
//.concat(") FEP ON DEA_PID_DEAL = FEP_PID_DEAL")
//.concat("LEFT JOIN (")
//.concat("SELECT FAC_PID_DEAL AS FAC_COUNT_PID_DEAL,COUNT(*) AS NO_OF_FACILITIES FROM LS2USER.VLS_FACILITY")
//.concat("WHERE FAC_DTE_TERM_FAC IS NULL")
//.concat("GROUP BY FAC_PID_DEAL")
//.concat(") FAC_COUNT ON DEA_PID_DEAL = FAC_COUNT_PID_DEAL")
//.concat("JOIN LS2USER.VLS_FACILITY FAC ON A.DEA_PID_DEAL = FAC_PID_DEAL")
//.concat("LEFT JOIN (")
//.concat("SELECT OST_PID_FACILITY AS AVAIL_FAC_PID,SUM(OST_AMT_CURRENT) AS FAC_AMT_OST FROM LS2USER.VLS_OUTSTANDING")
//.concat("WHERE OST_CDE_OUTSTD_TYP IN ('LOAN','SBLC') AND OST_CDE_OB_ST_CTG = 'ACTUA'")
//.concat("GROUP BY OST_PID_FACILITY")
//.concat(") AVAIL ON FAC_PID_FACILITY = AVAIL_FAC_PID")
//.concat("LEFT JOIN LS2USER.VLS_PROD_POS_CUR ON FAC_PID_FACILITY = PDC_PID_PRODUCT_ID")
//.concat("LEFT JOIN LS2USER.VLS_OBJ_EXT_ATR O1 ON FAC_PID_FACILITY = O1.OEA_RID_OWNER AND O1.OEA_CDE_OWNER_TYPE = 'FAC'")
//.concat("AND O1.OEA_TXT_NAME = 'PREPAYMENT PENALTY CALC TYPE'")
//.concat("LEFT JOIN LS2USER.VLS_OBJ_EXT_ATR O2 ON FAC_PID_FACILITY = O2.OEA_RID_OWNER AND O2.OEA_CDE_OWNER_TYPE = 'FAC'")
//.concat("AND O2.OEA_TXT_NAME = 'PREPAYMENT PENALTY EFFECTIVE DATE'")
//.concat("LEFT JOIN LS2USER.VLS_OBJ_EXT_ATR O5 ON FAC_PID_FACILITY = O5.OEA_RID_OWNER AND O5.OEA_CDE_OWNER_TYPE = 'FAC'")
//.concat("AND O5.OEA_TXT_NAME = 'ENCOMPASS ORIGINATED'")
//.concat("LEFT JOIN (")
//.concat("SELECT FAC_PID_FACILITY AS LGD_FAC_PID,GB3_DSC_CODE AS LGD_DESC,IRT_DTE_RATING_EFF AS LGD_RATING_EFF_DATE")
//.concat("FROM")
//.concat("LS2USER.VLS_DEAL JOIN LS2USER.VLS_FACILITY ON DEA_PID_DEAL = FAC_PID_DEAL")
//.concat("LEFT JOIN LS2USER.VLS_INTERNAL_RTG ON FAC_PID_FACILITY = IRT_PID_PRODUCT_ID")
//.concat("AND IRT_CDE_INTRTG_TYP = 'LGD' AND IRT_DTE_RATING_EXP IS NULL")
//.concat("LEFT JOIN LS2USER.VLS_FAM_GLOBAL3 ON IRT_CDE_INTRL_RTG = GB3_CDE_CODE AND GB3_TID_TABLE_ID = 'RRC'")
//.concat(") LGD ON FAC_PID_FACILITY = LGD_FAC_PID")
//.concat("LEFT JOIN (")
//.concat("SELECT FAC_PID_FACILITY AS PD_FAC_PID,GB3_DSC_CODE AS PD_DESC,IRT_DTE_RATING_EFF AS PD_RATING_EFF_DATE")
//.concat("FROM")
//.concat("LS2USER.VLS_DEAL JOIN LS2USER.VLS_FACILITY ON DEA_PID_DEAL = FAC_PID_DEAL")
//.concat("LEFT JOIN LS2USER.VLS_INTERNAL_RTG ON FAC_PID_FACILITY = IRT_PID_PRODUCT_ID")
//.concat("AND IRT_CDE_INTRTG_TYP = 'PD' AND IRT_DTE_RATING_EXP IS NULL")
//.concat("LEFT JOIN LS2USER.VLS_FAM_GLOBAL3 ON IRT_CDE_INTRL_RTG = GB3_CDE_CODE AND GB3_TID_TABLE_ID = 'RRC'")
//.concat(") PD ON FAC_PID_FACILITY = PD_FAC_PID")
//.concat("JOIN LS2USER.VLS_OUTSTANDING LOAN ON FAC_PID_FACILITY = LOAN.OST_PID_FACILITY")
//.concat("AND LOAN.OST_CDE_OUTSTD_TYP = 'LOAN' AND LOAN.OST_CDE_OB_ST_CTG = 'ACTUA'")
//.concat("LEFT JOIN LS2USER.VLS_CUSTOMER LOAN_CUST ON OST_CID_BORROWER = LOAN_CUST.CUS_CID_CUST_ID")
//.concat("LEFT JOIN LS2USER.VLS_OBJ_EXT_ATR OFFICIAL ON LOAN_CUST.CUS_CID_CUST_ID = OFFICIAL.OEA_RID_OWNER AND OFFICIAL.OEA_TXT_NAME = 'OFFICIAL TYPE'")
//.concat("LEFT JOIN LS2USER.VLS_OST_RATES ON OST_RID_OUTSTANDNG = ORT_RID_OUTSTANDNG")
//.concat("LEFT JOIN LS2USER.VRP_#AH_LOAN_COF V1 ON LOAN.OST_RID_OUTSTANDNG = V1.COF_RID_OUTSTANDNG")
//.concat("AND DEA_CDE_BRANCH = V1.OCS_CDE_BRANCH")
//.concat("LEFT JOIN LS2USER.VLS_LATE_CHG_RULE ON LOAN.OST_RID_LCHG_RULE = LCR_RID_RULE")
//.concat("LEFT JOIN LS2USER.VLS_SCHEDULE ON LOAN.OST_RID_OUTSTANDNG = SCH_RID_OWNER")
//.concat("LEFT JOIN ")
//.concat("(")
//.concat("SELECT SCI_RID_SCHEDULE,SCI_DTE_EFFECTIVE AS LAST_PMT_DATE,ABS(SCI_AMT_ITEM + SCI_AMT_PRIN_PAID) AS LAST_PRIN_PMT_AMT")
//.concat(",ROW_NUMBER() OVER (PARTITION BY SCI_RID_SCHEDULE ORDER BY SCI_DTE_EFFECTIVE DESC) AS ROW_NUM")
//.concat("FROM")
//.concat("LS2USER.VLS_SCHEDULE_ITEM")
//.concat("WHERE ABS(SCI_AMT_ITEM + SCI_AMT_PRIN_PAID) > 0")
//.concat(") LAST_PMT ON SCH_RID_SCHEDULE = LAST_PMT.SCI_RID_SCHEDULE AND LAST_PMT.ROW_NUM = 1")
//.concat("LEFT JOIN (")
//.concat("SELECT SCI_RID_SCHEDULE,SCI_RID_ITEM,SCI_DTE_EFFECTIVE, SCI_CDE_F_BAL_TYPE,ROW_NUMBER() OVER (PARTITION BY SCI_RID_SCHEDULE ORDER BY SCI_DTE_EFFECTIVE) AS ROW_NUM")
//.concat("FROM")
//.concat("LS2USER.VLS_SCHEDULE_ITEM")
//.concat("WHERE (SCI_AMT_ITEM + SCI_AMT_PRIN_PAID) <> 0 OR (COALESCE(SCI_AMT_INTEREST,0) + COALESCE(SCI_AMT_INT_PAID,0)) <> 0")
//.concat(") SCI ON SCH_RID_SCHEDULE = SCI.SCI_RID_SCHEDULE AND SCI.ROW_NUM = 1")
//.concat("LEFT JOIN (")
//.concat("SELECT DAM_PID_DEAL,COUNT(DAM_RID_AMENDMENT) AS NO_OF_PRIORAMENDS,MAX(DAM_DTE_EFFECTIVE) AS PRIORAMEND_LAST_EFF_DATE")
//.concat(",MAX(DAM_TSP_REC_UPDATE) AS PRIORAMEND_LAST_TSP")
//.concat("FROM")
//.concat("LS2USER.VLS_DEAL_AMENDMENT")
//.concat("GROUP BY DAM_PID_DEAL) DAM ON A.DEA_PID_DEAL = DAM_PID_DEAL")
//.concat("LEFT JOIN LS2USER.VLS_OBJ_EXT_ATR O3 ON OST_RID_OUTSTANDNG = O3.OEA_RID_OWNER AND O3.OEA_TXT_NAME = 'COF SPREAD LOCK EXPIRY DATE'")
//.concat("LEFT JOIN LS2USER.VLS_OBJ_EXT_ATR O4 ON OST_RID_OUTSTANDNG = O4.OEA_RID_OWNER AND O4.OEA_TXT_NAME = 'RATE LOCK DATE'")
//.concat("WHERE")
//.concat("--CAST(DEA_TSP_REC_CREATE AS DATE) BETWEEN '2018-07-01' AND '2020-12-31'")
//.concat("DEA_CDE_BRANCH in ('011')")
//.concat("--DEA_CDE_BRANCH not in ('110','DNOTE')")
//.concat("and DEA_UID_REC_CREATE  LIKE 'LIQAPI%'")
//.concat("and FAC_CDE_FAC_TYPE = 'TAM'")
//.concat("and LOAN.OST_CDE_PRICE_OPT = 'FIXTM'")
//.concat("ORDER BY A.DEA_TSP_REC_CREATE DESC");
//
//        try {
//            String connectionUrl = "jdbc:sqlserver://"+ EnvName + EnvDomain +":1433;databaseName=FBCME;user=SVCCMETESTUSER01;password=This1is2a3sqllogin"; //integratedSecurity=true";
//            Connection con = DriverManager.getConnection(connectionUrl);
//            Statement stmt = con.createStatement();
//            ResultSet rs = stmt.executeQuery(q);
//            while (rs.next()) {
//               conversionData.put("Primary Borrower", rs.getString("PRIM_BORROWER"));
//                conversionData.put("Secondary Borrowers", rs.getString("SEC_BORROWERS"));
//                conversionData.put("Facility Number", rs.getString("FAC_NUM_FAC_CNTL"));
//                conversionData.put("Loan Effective Date", rs.getString("FAC_DTE_EFFECTIVE"));
//                conversionData.put("Maturity Date", rs.getString("FAC_DTE_FINAL_MAT"));
//                conversionData.put("Current Commitment", rs.getString("FAC_CURRENT_CMT"));
//                conversionData.put("Loan IQ ID", rs.getString("LOANIQ_ID"));
//                conversionData.put("Customer Name", rs.getString("LOANIQ_ID"));
//               break;
//
//            }
//
//            con.close();
//
//            System.out.println(conversionData.get("Facility Number"));
//
//
//        }
//        // Handle any errors that may have occurred.
//        catch (SQLException e) {
//            e.printStackTrace();
//        }

//        String timeStamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new java.util.Date());
//        System.out.println(timeStamp);
//        String[] strArr = {timeStamp, "EnvName", "CmeRelVersion", "SqlRelVersion", "CmePrmVersion"};
//       String qry	="INSERT INTO [PERF_TEST_RESULTS].[dbo].[LoadMetrics] ([testID], [testEnvironment], [CMEReleaseVersion], [SQLReleaseVersion], [CMEPromoVersion])"
//       .concat("VALUES ('" + String.join("','", strArr) +"')");
//        System.out.println(qry);
//
//        try {
//            String connectionUrl = "jdbc:sqlserver://QASTIMPRDFA04:1433;databaseName=PERF_TEST_RESULTS;user=SVCCMETESTUSER01;password=This1is2a3sqllogin"; //integratedSecurity=true";
//
//            Connection con = DriverManager.getConnection(connectionUrl);PreparedStatement stmt = con.prepareStatement(qry);
//            stmt.executeUpdate();
//            con.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        try {
//            String csvFilePath = "target/html-report/ScreenLoadMetrics.csv";
//            BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
//            String lineText = null;
//            lineReader.readLine(); // skip header line
//
//            String qry	="INSERT INTO [PERF_TEST_RESULTS].[dbo].[LoadMetricStepDetails] ([testID], [testActivity], [testStepName], [testStepRunTime])"
//                    .concat("VALUES (?, ?, ?, ?)");
//
//
//            String connectionUrl = "jdbc:sqlserver://QASTIMPRDFA04:1433;databaseName=PERF_TEST_RESULTS;user=SVCCMETESTUSER01;password=This1is2a3sqllogin"; //integratedSecurity=true";
//
//            Connection con = DriverManager.getConnection(connectionUrl);
//            PreparedStatement statement = con.prepareStatement(qry);
//
//            while ((lineText = lineReader.readLine()) != null) {
//                String[] data = lineText.split(",");
//                String testID = data[0];
//                String activity = data[1];
//                String objName = data[2];
//                String elapsTime = data[3];
//
//                statement.setString(1, testID);
//                statement.setString(2, activity);
//                statement.setString(3, objName);
//                statement.setString(4, elapsTime);
//                statement.addBatch();
//                statement.executeBatch();
//            }
//            lineReader.close();
//            statement.executeBatch();
//            con.commit();
//            con.close();
//        }catch (IOException | SQLException e) {
//            Assert.fail(e.getMessage());
//        }


    String str = "kaylee johnson";

        System.out.println(WordUtils.capitalizeFully(str));


    }
    }

