(function () {
    function CmpCore () {
        this.componemtsClass = "component";
        this.cmps = {};
        this.elements = [];
    }

    CmpCore.prototype.registryComponent = function (config) {
        var cmp = new Component (config);
        this.cmps[config.name] = cmp;
    };

    CmpCore.prototype.addElement = function (config) {
        if (!config.name || !config.type || !config.container) {
            console.log("Нужео имя, тип и контейнер.");
            return;
        }

        if (!this.cmps[config.type]) {
            console.log("Не найден компонент " + config.type);
            return;
        }

        var element = new Element(config);

        //config.component = this.cmps[config.type];

        this.elements.push(element);
        return element;
    };

    CmpCore.prototype.drawAll = function (force) {
        for (var i = 0; i < this.elements.length; i++) {
            if (!this.elements[i].isDrawn || force) {
                this.elements[i].draw();
                this.elements[i].bind();
            }
        }
    };

    /*    CmpCore.prototype.getRootElement = function (name, container) {
            for (var i = 0; i < this.elements.length; i++) {
                var containerClear = this.elements[i].container.replace(".", "");
                if (this.elements[i].name === name && (!container || containerClear === container)) {
                    return this.elements[i];
                }
            }

            return null;
        };*/

    /*
     Component
     * config
     * name - тип компонента
     * templateId - id шаблона в html
     * events - События по дефолту
     * methods - методы по дефолту.
     *
     * */
    function Component (parameters) {
        if (!parameters.name || !parameters.templateId) {
            console.log("Ошибка при создании компонента.");
            return;
        }
        this.name = parameters.name;
        this.events = parameters.events;
        this.methods = parameters.methods;
        this.afterRender = parameters.afterRender;
        this.extends = parameters.extends;

        var template = document.getElementById(parameters.templateId);
        if (!template) {
            console.log("Шаблон указан неверно");
            return;
        }

        if (parameters.extends) {
            var parentCmp = cmpCore.cmps[this.extends];

            for (var method in parentCmp.methods) {
                if (this.methods[method]) {
                    continue;
                }

                this.methods[method] = parentCmp.methods[method];
            }
        }

        this.htmlContent = _.template(template.innerHTML);
        // this.templateElm = template;
    }

    /**
     * Element
     * @param config
     * @constructor
     * model
     * name
     * container
     * actions
     * component
     * properties - Кастомные свойства.
     **      hidden: резервированное св-во для скрытия компонента
     * methods - для кастомных Методов
     * temp - Временное хранилище переменных (таймеров)
     * childred - дочерние компоненты...
     * commonCmpMap - мапа с потомками (для поиска по имени). Есть только у детей cmpCore
     * rootElm - ссылка на крневой элемент (у которого есть commonCmpMap)
     *
     * component.methods.preAction - резервированный метод проверки запуска событий события.
     */

    function Element (config) {
        var self = this;
        self.model = config.model;
        self.name = config.name;
        self.container = config.container;
        self.actions = config.actions;
        self.component = cmpCore.cmps[config.type];
        self.properties = config.properties || {};
        self.constructor = config.constructor;
        self.addAfterRender = config.addAfterRender;
        self.temp = {};
        self.children = [];
        self.childNames = {};
        self.rootElm = config.rootElm || self;
        self.commonCmpMap = {};

        if (self.rootElm !== self) {
            self.rootElm.commonCmpMap[self.name] = self;
        }

        if (config.children) {
            // Добавляем дочерние компоненты.
            for (var i = 0; i < config.children.length; i++) {
                config.children[i].rootElm = self.rootElm;

                var child = new Element(config.children[i]);
                self.children.push(child);
                self.childNames[child.name] = i;
            }
        }

        //this.methods = config.methods;

        if (this.component.methods) {
            for (var method in self.component.methods) {
                if (!config.methods || !config.methods[method]) {
                    this[method] = (function () {
                        var methodClosure = method;
                        return function (params) {
                            return self.component.methods[methodClosure].call(self, params);
                        }
                    })();
                }
            }
        }

        if (config.methods) {
            for (var method in config.methods) {
                this[method] = config.methods[method];
            }
        }
    }

    Element.prototype.findByName = function (name) {
        return this.commonCmpMap[name];
    };

    Element.prototype.updateModel = function (childConfig) {
        this.model = childConfig.model;

        if (!childConfig.children || childConfig.children.length === 0) {
            return;
        }

        this.addChildren(childConfig.children);
    };

    Element.prototype.addChildren = function (children) {

        if (!children || children.length === 0) {
            return;
        }

        // Добавляем дочерние компоненты.
        for (var i = 0; i < children.length; i++) {

            var childNum = this.childNames[children[i].name];
            if (childNum || childNum === 0) {
                this.children[childNum].updateModel(children[i]);
                continue;
            }

            children[i].rootElm = this.rootElm;
            var child = new Element(children[i]);
            this.children.push(child);
            var childLength = this.children.length - 1;
            this.childNames[child.name] = i;
        }
    };

    /**
     * --Вызывается при изъятии компонента из DOM
     *
     * Вызывается руками.
     * для освобождения ресурсов.
     * @param innerDocument
     */
    Element.prototype.finalize = function (innerDocument) {

        if (this.destroy) {
            this.destroy();
        }

        // Добавляем дочерние компоненты.
        for (var i = 0; i < this.children.length; i++) {
            this.children[i].finalize();
        }
    };

    /**
     * innerDocument - Для рендеринга дочерних компонентов...
     * @param innerDocument
     */
    Element.prototype.draw = function (innerDocument) {

        if (this.properties.hidden) {
            // не рендерим.
            return;
        }

        //this.addBeforeRender();
        this.render(innerDocument);

        /*if (this.component.afterRender) {
            this.component.afterRender.call(this);
        }

        if (this.addAfterRender) {
            this.addAfterRender();
        }*/
    };

    Element.prototype.render = function (innerDocument) {

        if (!innerDocument) {
            innerDocument = document;
        }

        var self = this;
        var htmlContainer = innerDocument.querySelector(self.container);
        if (!htmlContainer) {
            console.log("Контейнер указан неверно");
            return;
        }
        self.containerElm = htmlContainer;

        if (self.constructor) {
            self.constructor.call(self);
        }

        var elementHtml = self.component.htmlContent({data: self.model});

        // Могут быть подкомпоненты.
        if (self.children && self.children.length > 0) {
            var parser = new DOMParser();
            var tmpDocument = parser.parseFromString(elementHtml, "text/html");

            for (var i = 0; i < self.children.length; i++) {
                self.children[i].draw(tmpDocument);
            }

            elementHtml = tmpDocument.body.outerHTML;
            delete tmpDocument;
        }

        htmlContainer.innerHTML = elementHtml;

        self.isDrawn = true;
    };

    /**
     * Добавление обработчиков событий
     * @param container
     */

    Element.prototype.bind = function (container) {
        var self = this;

        if (self.properties.hidden) {
            /*if (self.isDrawn && self.finalize) {
                self.finalize();
            }*/
            // не рендерим.
            return;
        }

        if (!container) {
            container = self.containerElm;
        }

        var htmlContainer = container;

        for (var event in self.component.events) {
            htmlContainer.addEventListener(event, function (evt) {
                self.component.events[event].call(self, evt);
            }, true);
        }

        for (var event in self.actions) {
            //htmlContainer.addEventListener(event, self.actions[event], true);
            htmlContainer.addEventListener(event, (function () {
                var evt = event;
                return function (e) {
                    if (self.component.methods.preAction) {
                        var ans = self.component.methods.preAction.call(self, e);
                        if (!ans) {
                            return;
                        }
                    }

                    self.actions[evt].call(self, e);
                }
            })(), true);
        }

        if (self.children && self.children.length > 0) {
            for (var i = 0; i < self.children.length; i++) {
                var childContainerElm = htmlContainer.querySelector(self.children[i].container);
                self.children[i].containerElm = childContainerElm;

                self.children[i].bind(childContainerElm);
            }
        }

        if (self.component.afterRender) {
            self.component.afterRender.call(this);
        }

        if (self.addAfterRender) {
            self.addAfterRender.call(this);
        }
    };


    var cmpCore = new CmpCore();

    window.cmpCore = cmpCore;

})();
