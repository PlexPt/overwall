package cc.overwall.overwall.Bean;

import com.google.gson.annotations.SerializedName;

public class UserSet {

    /**
     * id : 175
     * user_name : PT
     * email : plexpt@gmail.com
     * pass : null
     * passwd : 10086
     * t : 1468928229
     * u : 9089159
     * d : 7076231418
     * plan : A
     * transfer_enable : 1099511627776
     * port : 20476
     * switch : 1
     * enable : 1
     * type : 1
     * last_get_gift_time : 0
     * last_check_in_time : 1468911764
     * last_rest_pass_time : 0
     * reg_date : 2016-04-08 23:23:04
     * invite_num : 1
     * money : 0.12
     * ref_by : 1
     * expire_time : 0
     * method : rc4-md5
     * is_email_verify : 0
     * reg_ip : 127.0.0.1
     * node_speedlimit : 0.00
     * node_connector : 1
     * is_admin : false
     * im_type : 1
     * im_value : 1125094786
     * last_day_t : 7049825254
     * sendDailyMail : 1
     * class : 100
     * class_expire : 2017-04-08 23:36:28
     * expire_in : 2017-05-08 23:23:04
     * theme : material
     * ga_token : A346IDPMAUCRUEE2
     * ga_enable : 0
     * pac : null
     * remark : null
     * node_group : 0
     * auto_reset_day : 0
     * auto_reset_bandwidth : 0.00
     * relay_enable : 0
     * relay_info : null
     * protocol : origin
     * protocol_param : null
     * obfs : plain
     * obfs_param : null
     * forbidden_ip : null
     * forbidden_port : null
     * disconnect_ip : null
     */

    private int id;
    private String user_name;
    private String email;
    private Object pass;
    private String passwd;
    private int t;
    private int u;
    private long d;
    private String plan;
    private long transfer_enable;
    private int port;
    @SerializedName("switch")
    private int switchX;
    private int enable;
    private int type;
    private int last_get_gift_time;
    private int last_check_in_time;
    private int last_rest_pass_time;
    private String reg_date;
    private int invite_num;
    private String money;
    private int ref_by;
    private int expire_time;
    private String method;
    private int is_email_verify;
    private String reg_ip;
    private String node_speedlimit;
    private int node_connector;
    private boolean is_admin;
    private int im_type;
    private String im_value;
    private long last_day_t;
    private int sendDailyMail;
    @SerializedName("class")
    private int classX;
    private String class_expire;
    private String expire_in;
    private String theme;
    private String ga_token;
    private int ga_enable;
    private Object pac;
    private Object remark;
    private int node_group;
    private int auto_reset_day;
    private String auto_reset_bandwidth;
    private int relay_enable;
    private Object relay_info;
    private String protocol;
    private Object protocol_param;
    private String obfs;
    private Object obfs_param;
    private Object forbidden_ip;
    private Object forbidden_port;
    private Object disconnect_ip;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getPass() {
        return pass;
    }

    public void setPass(Object pass) {
        this.pass = pass;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public int getU() {
        return u;
    }

    public void setU(int u) {
        this.u = u;
    }

    public long getD() {
        return d;
    }

    public void setD(long d) {
        this.d = d;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public long getTransfer_enable() {
        return transfer_enable;
    }

    public void setTransfer_enable(long transfer_enable) {
        this.transfer_enable = transfer_enable;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getSwitchX() {
        return switchX;
    }

    public void setSwitchX(int switchX) {
        this.switchX = switchX;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getLast_get_gift_time() {
        return last_get_gift_time;
    }

    public void setLast_get_gift_time(int last_get_gift_time) {
        this.last_get_gift_time = last_get_gift_time;
    }

    public int getLast_check_in_time() {
        return last_check_in_time;
    }

    public void setLast_check_in_time(int last_check_in_time) {
        this.last_check_in_time = last_check_in_time;
    }

    public int getLast_rest_pass_time() {
        return last_rest_pass_time;
    }

    public void setLast_rest_pass_time(int last_rest_pass_time) {
        this.last_rest_pass_time = last_rest_pass_time;
    }

    public String getReg_date() {
        return reg_date;
    }

    public void setReg_date(String reg_date) {
        this.reg_date = reg_date;
    }

    public int getInvite_num() {
        return invite_num;
    }

    public void setInvite_num(int invite_num) {
        this.invite_num = invite_num;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getRef_by() {
        return ref_by;
    }

    public void setRef_by(int ref_by) {
        this.ref_by = ref_by;
    }

    public int getExpire_time() {
        return expire_time;
    }

    public void setExpire_time(int expire_time) {
        this.expire_time = expire_time;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public int getIs_email_verify() {
        return is_email_verify;
    }

    public void setIs_email_verify(int is_email_verify) {
        this.is_email_verify = is_email_verify;
    }

    public String getReg_ip() {
        return reg_ip;
    }

    public void setReg_ip(String reg_ip) {
        this.reg_ip = reg_ip;
    }

    public String getNode_speedlimit() {
        return node_speedlimit;
    }

    public void setNode_speedlimit(String node_speedlimit) {
        this.node_speedlimit = node_speedlimit;
    }

    public int getNode_connector() {
        return node_connector;
    }

    public void setNode_connector(int node_connector) {
        this.node_connector = node_connector;
    }

    public boolean isIs_admin() {
        return is_admin;
    }

    public void setIs_admin(boolean is_admin) {
        this.is_admin = is_admin;
    }

    public int getIm_type() {
        return im_type;
    }

    public void setIm_type(int im_type) {
        this.im_type = im_type;
    }

    public String getIm_value() {
        return im_value;
    }

    public void setIm_value(String im_value) {
        this.im_value = im_value;
    }

    public long getLast_day_t() {
        return last_day_t;
    }

    public void setLast_day_t(long last_day_t) {
        this.last_day_t = last_day_t;
    }

    public int getSendDailyMail() {
        return sendDailyMail;
    }

    public void setSendDailyMail(int sendDailyMail) {
        this.sendDailyMail = sendDailyMail;
    }

    public int getClassX() {
        return classX;
    }

    public void setClassX(int classX) {
        this.classX = classX;
    }

    public String getClass_expire() {
        return class_expire;
    }

    public void setClass_expire(String class_expire) {
        this.class_expire = class_expire;
    }

    public String getExpire_in() {
        return expire_in;
    }

    public void setExpire_in(String expire_in) {
        this.expire_in = expire_in;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getGa_token() {
        return ga_token;
    }

    public void setGa_token(String ga_token) {
        this.ga_token = ga_token;
    }

    public int getGa_enable() {
        return ga_enable;
    }

    public void setGa_enable(int ga_enable) {
        this.ga_enable = ga_enable;
    }

    public Object getPac() {
        return pac;
    }

    public void setPac(Object pac) {
        this.pac = pac;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public int getNode_group() {
        return node_group;
    }

    public void setNode_group(int node_group) {
        this.node_group = node_group;
    }

    public int getAuto_reset_day() {
        return auto_reset_day;
    }

    public void setAuto_reset_day(int auto_reset_day) {
        this.auto_reset_day = auto_reset_day;
    }

    public String getAuto_reset_bandwidth() {
        return auto_reset_bandwidth;
    }

    public void setAuto_reset_bandwidth(String auto_reset_bandwidth) {
        this.auto_reset_bandwidth = auto_reset_bandwidth;
    }

    public int getRelay_enable() {
        return relay_enable;
    }

    public void setRelay_enable(int relay_enable) {
        this.relay_enable = relay_enable;
    }

    public Object getRelay_info() {
        return relay_info;
    }

    public void setRelay_info(Object relay_info) {
        this.relay_info = relay_info;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public Object getProtocol_param() {
        return protocol_param;
    }

    public void setProtocol_param(Object protocol_param) {
        this.protocol_param = protocol_param;
    }

    public String getObfs() {
        return obfs;
    }

    public void setObfs(String obfs) {
        this.obfs = obfs;
    }

    public Object getObfs_param() {
        return obfs_param;
    }

    public void setObfs_param(Object obfs_param) {
        this.obfs_param = obfs_param;
    }

    public Object getForbidden_ip() {
        return forbidden_ip;
    }

    public void setForbidden_ip(Object forbidden_ip) {
        this.forbidden_ip = forbidden_ip;
    }

    public Object getForbidden_port() {
        return forbidden_port;
    }

    public void setForbidden_port(Object forbidden_port) {
        this.forbidden_port = forbidden_port;
    }

    public Object getDisconnect_ip() {
        return disconnect_ip;
    }

    public void setDisconnect_ip(Object disconnect_ip) {
        this.disconnect_ip = disconnect_ip;
    }
}
