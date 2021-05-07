package com.pxy._import.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author puxy
 * @version 1.0
 * @description 通过ImportSelector 方式导入的类,(感觉这种方式没啥用)
 * @date 2021/1/25 15:24
 */
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"com.pxy._import.pojo.ObjectB"};
    }
}
