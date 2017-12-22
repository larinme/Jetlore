package com.entity;

import com.entity.presenters.Presenter;
import com.utils.PropertyFinder;

public enum TokenType implements Presenter {

    ENTITY {
        public String getTemplate() {
            return PropertyFinder.getInstance().getProperty("template_entity");
        }
    },
    LINK {
        public String getTemplate() {
            return PropertyFinder.getInstance().getProperty("template_link");
        }
    },
    USERNAME {
        public String getTemplate() {
            return PropertyFinder.getInstance().getProperty("template_username");
        }
    },
    PLAINTEXT {
        public String getTemplate() {
            return PropertyFinder.getInstance().getProperty("template_plaintext");
        }
    };

    public abstract String getTemplate();
}
