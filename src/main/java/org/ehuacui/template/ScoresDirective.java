package org.ehuacui.template;

import freemarker.core.Environment;
import freemarker.template.*;
import org.ehuacui.common.ServiceHolder;
import org.ehuacui.module.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ehuacui.
 * Copyright (c) 2016, All Rights Reserved.
 * http://www.ehuacui.org
 */
public class ScoresDirective implements TemplateDirectiveModel {

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels,
                        TemplateDirectiveBody templateDirectiveBody)
            throws TemplateException, IOException {
        List<User> scores = new ArrayList<User>();
        if (map.containsKey("limit") && map.get("limit") != null) {
            scores = ServiceHolder.userService.scores(Integer.parseInt(map.get("limit").toString()));
        }
        DefaultObjectWrapperBuilder builder = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_23);
        environment.setVariable("list", builder.build().wrap(scores));
        templateDirectiveBody.render(environment.getOut());
    }

}
