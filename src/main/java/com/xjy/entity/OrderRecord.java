package com.xjy.entity;

import java.io.Serializable;
import java.util.Date;

import com.xjy.enums.OrderStatusEnum;
import com.xjy.util.DateUtils;

public class OrderRecord implements Serializable {
    private Integer id;

    private String orderId;

    private String identityNum;

    private String name;

    private String mobile;

    private String type;

    private String area;

    private String address;

    private String detail;

    private String reserveDateStart;

    private String reserveDateEnd;

    private String status;

    private String jobNum;

    private String jobName;

    private String remark;

    private Date createTime;

    private Date modifyTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getIdentityNum() {
        return identityNum;
    }

    public void setIdentityNum(String identityNum) {
        this.identityNum = identityNum == null ? null : identityNum.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getReserveDateStart() {
        return reserveDateStart;
    }

    public void setReserveDateStart(String reserveDateStart) {
        this.reserveDateStart = reserveDateStart;
    }

    public String getReserveDateEnd() {
        return reserveDateEnd;
    }

    public void setReserveDateEnd(String reserveDateEnd) {
        this.reserveDateEnd = reserveDateEnd;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusStr() {
        return OrderStatusEnum.valueOf(status).getName();
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getJobNum() {
        return jobNum;
    }

    public void setJobNum(String jobNum) {
        this.jobNum = jobNum == null ? null : jobNum.trim();
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getCreateTimeStr() {
        return DateUtils.parseDate(createTime);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public String getModifyTimeStr() {
        return DateUtils.parseDate(modifyTime);
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", orderId=").append(orderId);
        sb.append(", identityNum=").append(identityNum);
        sb.append(", name=").append(name);
        sb.append(", mobile=").append(mobile);
        sb.append(", type=").append(type);
        sb.append(", area=").append(area);
        sb.append(", address=").append(address);
        sb.append(", detail=").append(detail);
        sb.append(", reserveDateStart=").append(reserveDateStart);
        sb.append(", reserveDateEnd=").append(reserveDateEnd);
        sb.append(", status=").append(status);
        sb.append(", jobNum=").append(jobNum);
        sb.append(", jobName=").append(jobName);
        sb.append(", remark=").append(remark);
        sb.append(", createTime=").append(createTime);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
