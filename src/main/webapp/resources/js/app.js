document.addEventListener("DOMContentLoaded", function () {

    $("#btnDelivery").on("click", function () {
        if ($('#datePickUp').val() == '') {
            alert('Proszę wybierz date kiedy paczka zostala odebrana');
            return false;
        } else if (($("input[name='pickedUp']:checked").val() == null)) {
            alert('Proszę zaznacz odpowiedni checkbox');
            return false;
        }
    });

        /**
         * Form Select
         */
        class FormSelect {
            constructor($el) {
                this.$el = $el;
                this.options = [...$el.children];
                this.init();
            }

            init() {
                this.createElements();
                this.addEvents();
                this.$el.parentElement.removeChild(this.$el);
            }

            createElements() {
                // Input for value
                this.valueInput = document.createElement("input");
                this.valueInput.type = "text";
                this.valueInput.name = this.$el.name;

                // Dropdown container
                this.dropdown = document.createElement("div");
                this.dropdown.classList.add("dropdown");

                // List container
                this.ul = document.createElement("ul");

                // All list options
                this.options.forEach((el, i) => {
                    const li = document.createElement("li");
                    li.dataset.value = el.value;
                    li.innerText = el.innerText;

                    if (i === 0) {
                        // First clickable option
                        this.current = document.createElement("div");
                        this.current.innerText = el.innerText;
                        this.dropdown.appendChild(this.current);
                        this.valueInput.value = el.value;
                        li.classList.add("selected");
                    }

                    this.ul.appendChild(li);
                });

                this.dropdown.appendChild(this.ul);
                this.dropdown.appendChild(this.valueInput);
                this.$el.parentElement.appendChild(this.dropdown);
            }

            addEvents() {
                this.dropdown.addEventListener("click", e => {
                    const target = e.target;
                    this.dropdown.classList.toggle("selecting");

                    // Save new value only when clicked on li
                    if (target.tagName === "LI") {
                        this.valueInput.value = target.dataset.value;
                        this.current.innerText = target.innerText;
                    }
                });
            }
        }

        document.querySelectorAll(".form-group--dropdown select").forEach(el => {
            new FormSelect(el);
        });

        /**
         * Hide elements when clicked on document
         */
        document.addEventListener("click", function (e) {
            const target = e.target;
            const tagName = target.tagName;

            if (target.classList.contains("dropdown")) return false;

            if (tagName === "LI" && target.parentElement.parentElement.classList.contains("dropdown")) {
                return false;
            }

            if (tagName === "DIV" && target.parentElement.classList.contains("dropdown")) {
                return false;
            }

            document.querySelectorAll(".form-group--dropdown .dropdown").forEach(el => {
                el.classList.remove("selecting");
            });
        });

        /**
         * Switching between form steps
         */
        class FormSteps {
            constructor(form) {
                this.$form = form;
                this.$next = form.querySelectorAll(".next-step");
                this.$prev = form.querySelectorAll(".prev-step");
                this.$step = form.querySelector(".form--steps-counter span");
                this.currentStep = 1;

                this.$stepInstructions = form.querySelectorAll(".form--steps-instructions p");
                const $stepForms = form.querySelectorAll("form > div");
                this.slides = [...this.$stepInstructions, ...$stepForms];

                this.init();
            }

            /**
             * Init all methods
             */
            init() {
                this.events();
                this.updateForm();
            }

            /**
             * All events that are happening in form
             */
            events() {

                // Next step
                this.$next.forEach(btn => {
                    btn.addEventListener("click", e => {

                            let quantityForm = $('#quantityForm').val();
                            let institutionForm = $("input[name='institution']:checked").val();
                            let streetForm = $('#streetForm').val();
                            let cityForm = $('#cityForm').val();
                            let zipCodeForm = $('#zipCodeForm').val();
                            let telephoneForm = $('#telephoneForm').val();
                            let dateForm = $('#dateForm').val();
                            let timeForm = $('#timeForm').val();
                            let description = $('#description').val();


                            // $("input[name='categories']").on('change', function () {
                            //     $("input[name='categories']:checked").css("background-color", "green");
                            // });

                            if ($("input[name='categories']:checked").val() == null) {
                                alert('Proszę wybierz rodzaj kategorii');
                                return false;
                            }

                            if ((quantityForm == 0 || quantityForm == '') && this.currentStep == 2) {
                                alert('Ilośc worków nie może być pusta bądź równa 0');
                                return false;
                            }

                            if (institutionForm == null && this.currentStep == 3) {
                                alert('Proszę wybierz rodzaj instytucji');
                                return false;
                            }

                            if ((streetForm == '' || cityForm == '' || zipCodeForm == '' || telephoneForm == '') && this.currentStep == 4) {
                                alert('Proszę uzupełnij dane adresowe');
                                return false;
                            } else if (timeForm == '' && this.currentStep == 4) {
                                alert('Proszę wybierz czas odbioru przesyłki');
                                return false;
                            } else if (dateForm == '' && this.currentStep == 4) {
                                alert('Proszę wybierz date odbioru przesyłki');
                                return false;
                            }

                            $('#timeFormCompleted').text(timeForm);
                            $('#dateFormCompleted').text(dateForm);
                            $('#quantityFormCompleted').text(quantityForm);
                            $('#institutionFormCompleted').text($("input[name='institution']:checked").attr('title'));
                            $('#streetFormCompleted').text(streetForm);
                            $('#cityFormCompleted').text(cityForm);
                            $('#zipCodeFormCompleted').text(zipCodeForm);
                            $('#telephoneFormCompleted').text(telephoneForm);
                            $('#descriptionFormCompleted').text(description);
                            e.preventDefault();
                            this.currentStep++;
                            this.updateForm();
                        }
                    )
                    ;
                });

                // Previous step
                this.$prev.forEach(btn => {
                    btn.addEventListener("click", e => {
                        e.preventDefault();
                        this.currentStep--;
                        this.updateForm();
                    });
                });

                // Form submit
                this.$form.querySelector("form").addEventListener("submit", e => this.submit(e));
            }

            /**
             * Update form front-end
             * Show next or previous section etc.
             */
            updateForm() {
                this.$step.innerText = this.currentStep;

                this.slides.forEach(slide => {
                    slide.classList.remove("active");

                    if (slide.dataset.step == this.currentStep) {
                        slide.classList.add("active");
                    }
                });

                this.$stepInstructions[0].parentElement.parentElement.hidden = this.currentStep >= 5;
                this.$step.parentElement.hidden = this.currentStep >= 5;

            }

        }

        const
            form = document.querySelector(".form--steps");

        if (form

            !==
            null
        ) {
            new

            FormSteps(form);
        }
    }


);