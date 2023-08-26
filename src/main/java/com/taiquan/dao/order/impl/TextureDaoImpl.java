package com.taiquan.dao.order.impl;

import com.taiquan.dao.BaseDaoHibernate5;
import com.taiquan.dao.order.TextureDao;
import com.taiquan.domain.order.Texture;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

@Repository("textureDao")
public class TextureDaoImpl extends BaseDaoHibernate5<Texture> implements TextureDao {
}
