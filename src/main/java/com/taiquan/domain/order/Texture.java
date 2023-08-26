package com.taiquan.domain.order;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import org.hibernate.annotations.Cache;

@Entity
@Table(name = "texture")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Texture {
    @Id
    private String textureName;
    @NumberFormat(pattern = "##.##")
    private float density;
    private String alias;
    private String guobiao;

    public String getTextureName() {
        return textureName;
    }

    public void setTextureName(String textureName) {
        this.textureName = textureName;
    }

    public float getDensity() {
        return density;
    }

    public void setDensity(float density) {
        this.density = density;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getGuobiao() {
        return guobiao;
    }

    public void setGuobiao(String guobiao) {
        this.guobiao = guobiao;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("textureName", textureName)
                .append("density", density)
                .append("alias",alias)
                .toString();
    }
}
