/**
 * Copyright 2013 Marc Gabriel-Willem (@mgwillem)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mmx.todo.core.boundary;

import com.mmx.todo.core.control.TodoRepositoryControl;
import com.mmx.todo.core.entity.Todo;
import org.slf4j.Logger;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
@Path("/todo")
public class TodoService {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(TodoService.class);

    @Inject
    private TodoRepositoryControl todoRepositoryControl;

    @GET
    @Produces({"application/json"})
    public List<Todo> findAll() {
        logger.info("findAll");

        return todoRepositoryControl.findAll();
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Todo find(@PathParam("id") Long id) {
        logger.info("find id:{}", id);

        return todoRepositoryControl.get(id);
    }

    @POST
    public void create(Todo todo) {
        logger.info("create {}", todo);

        todoRepositoryControl.create(todo);
    }

    @PUT
    @Path("{id}")
    public void update(Todo todo) {
        logger.info("update {}", todo);

        todoRepositoryControl.update(todo);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id) {
        logger.info("delete id:{}", id);

        todoRepositoryControl.delete(id);
    }
}
