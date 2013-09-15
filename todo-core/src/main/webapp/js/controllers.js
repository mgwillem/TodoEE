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

'use strict';

/* Controllers */

function ListController($scope, TodoService, $location) {
    $scope.todos = TodoService.query();
    $scope.deleteTodo = function (todo) {
        todo.$delete(function () {
            $location.path("/list");
        });
    };
    $scope.toggleTodo = function (todo) {
        todo.$update(function () {
            $location.path('/list');
        });
    };
    $scope.todosLeft = function () {
        return $scope.todos.filter(function (t) {
            return ! t.done;
        });
    };
}
ListController.$inject = ['$scope', 'TodoService', '$location'];


function AddController($scope, TodoService, $routeParams, $location) {
    $scope.todo = new TodoService();
    $scope.saveTodo = function () {
        TodoService.save($scope.todo, function () {
            $location.path('/list');
        });
    };
}
AddController.$inject = ['$scope', 'TodoService', '$routeParams', '$location'];


function EditController($scope, TodoService, $routeParams, $location) {
    $scope.todo = TodoService.get({id: $routeParams.id});
    $scope.saveTodo = function () {
        TodoService.update($scope.todo, function () {
            $location.path('/list');
        });
    };
}
EditController.$inject = ['$scope', 'TodoService', '$routeParams', '$location'];


function DisplayController($scope, TodoService, $routeParams) {
    $scope.todo = TodoService.get({id: $routeParams.id});
}
DisplayController.$inject = ['$scope', 'TodoService', '$routeParams'];
