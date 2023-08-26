package com.taiquan.domain.customer;

import com.taiquan.domain.BaseDomain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "companyLog")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CompanyLog extends BaseDomain {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  companyLogId;
    @DateTimeFormat(pattern = "yyyy-MM-dd",iso= DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    private Date addTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd",iso= DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    private Date updateTime;

    public CompanyLog() {
        this.addTime = new Date();
        this.updateTime = new Date();
    }

    public int getCompanyLogId() {
        return companyLogId;
    }

    public void setCompanyLogId(int companyLogId) {
        this.companyLogId = companyLogId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
