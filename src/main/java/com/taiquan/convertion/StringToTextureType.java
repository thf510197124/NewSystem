package com.taiquan.convertion;

import com.taiquan.domain.order.enums.TextureType;
import org.springframework.core.convert.converter.Converter;


public class StringToTextureType implements Converter<String, TextureType> {
    @Override
    public TextureType convert(String s) {
        for (TextureType textureType : TextureType.values()){
            if (textureType.getName().equals(s.trim()) || TextureType.valueOf(s.trim()) == textureType){
                return textureType;
            }
        }
        return null;
    }
}
