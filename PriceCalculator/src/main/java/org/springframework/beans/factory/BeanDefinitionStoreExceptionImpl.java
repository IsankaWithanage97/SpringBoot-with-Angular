package org.springframework.beans.factory;

public class BeanDefinitionStoreExceptionImpl extends BeanDefinitionStoreException {
    public BeanDefinitionStoreExceptionImpl(String msg) {
        super(msg);
    }

    public BeanDefinitionStoreExceptionImpl(String msg, Throwable cause) {
        super(msg, cause);
    }

    public BeanDefinitionStoreExceptionImpl(String resourceDescription, String msg) {
        super(resourceDescription, msg);
    }

    public BeanDefinitionStoreExceptionImpl(String resourceDescription, String msg, Throwable cause) {
        super(resourceDescription, msg, cause);
    }

    public BeanDefinitionStoreExceptionImpl(String resourceDescription, String beanName, String msg) {
        super(resourceDescription, beanName, msg);
    }

    public BeanDefinitionStoreExceptionImpl(String resourceDescription, String beanName, String msg, Throwable cause) {
        super(resourceDescription, beanName, msg, cause);
    }
}
