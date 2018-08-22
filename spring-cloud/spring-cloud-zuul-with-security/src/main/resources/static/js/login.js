var Login = function () {
    var handleLogin = function () {
        $('.login-form').validate({
            errorElement: 'span', //default input error message container
            errorClass: 'help-block', // default input error message class
            focusInvalid: false, // do not focus the last invalid input
            rules: {
                username: {
                    required: true
                },
                password: {
                    required: true
                },
                remember: {
                    required: false
                }
            },

            messages: {
                username: {
                    required: "Username is required."
                },
                password: {
                    required: "Password is required."
                }
            },

            invalidHandler: function (event, validator) { //display error alert on form submit
                $('.form_value', $('.login-form')).show();
            },

            highlight: function (element) { // hightlight error inputs
                $(element)
                    .closest('.form-group').addClass('has-error'); // set error class to the control group
            },

            success: function (label) {
                $(".form_value").hide();
                label.closest('.form-group').removeClass('has-error');
                label.remove();
            },

            errorPlacement: function (error, element) {
                error.insertAfter(element.closest('.input-icon'));
            },

            submitHandler: function (form) {
                form.submit(); // form validation success, call ajax form submit
            }
        });

        $('.login-form input').keypress(function (e) {
            if (e.which == 13) {
                if ($('.login-form').validate().form()) {
                    $('.login-form').submit(); //form validation success, call ajax form submit
                }
                return false;
            }
        });
    };

    return {
        init: function () {
            handleLogin();
        }
    };
}();

$.ajax({
    url: "/login/start/captcha?t=" + (new Date()).getTime(), // 加随机数防止缓存
    type: "get",
    dataType: "json",
    success: function (data) {
        initGeetest({
            gt: data.gt,
            challenge: data.challenge,
            new_captcha: data.new_captcha,
            offline: !data.success,
            product: "popup",
            width: "100%"
        }, handler2);
    }
});

var handler2 = function (captchaObj) {
    $("#login").click(function (e) {
        if ($('.login-form').validate().form()) {
            var result = captchaObj.getValidate();
            if (!result) {
                $(".captcha").show();
            } else {
                $.ajax({
                    url: '/login/verify/login',
                    type: 'POST',
                    dataType: 'json',
                    data: {
                        geetest_challenge: result.geetest_challenge,
                        geetest_validate: result.geetest_validate,
                        geetest_seccode: result.geetest_seccode
                    },
                    success: function (data) {
                        if (data.status === 'success') {
                            $('.login-form').submit();
                        } else if (data.status === 'fail') {
                            captchaObj.reset();
                            console.log(data);
                        }
                    }
                })
            }
            e.preventDefault();
        }
    });
    captchaObj.appendTo("#captcha");
    captchaObj.onReady(function () {
        $("#wait1").hide();
    });
};