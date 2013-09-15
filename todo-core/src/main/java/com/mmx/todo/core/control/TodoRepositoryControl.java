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
package com.mmx.todo.core.control;

import com.mmx.todo.core.entity.Todo;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

@Singleton
@Startup
@ConcurrencyManagement(value = ConcurrencyManagementType.CONTAINER)
public class TodoRepositoryControl {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(TodoRepositoryControl.class);

    private Map<Long, Todo> repository;
    private AtomicLong ids;

    @PostConstruct
    void init() {
        logger.info("loading 'Todo' repository control");

        repository = new ConcurrentSkipListMap<Long, Todo>();
        ids = new AtomicLong(0);

        create(new Todo(1L, "Task 1", "This is the task 1 description.", true));
        create(new Todo(2L, "Task 2", "This is the task 2 description."));
        create(new Todo(3L, "Task 3", "This is the task 3 description."));
    }

    public void create(Todo todo) {
        long id = ids.getAndIncrement();
        todo.setId(id);

        repository.put(id, todo);
    }

    public List<Todo> findAll() {
        return new ArrayList<Todo>(repository.values());
    }

    public Todo get(Long id) {
        return repository.get(id);
    }

    public void update(Todo todo) {
        repository.put(todo.getId(), todo);
    }

    public boolean delete(Long id) {
        return repository.remove(id) != null;
    }
}
