$(function(){

    $(".button-collapse").sideNav({edge: 'right'});


    $('[data-close]').on('click',function(){
        $(this).parent().remove();
    });


    $('input[type=file]').on("change",function(){
        var value = $(this).val().split('\\').pop();
        value = value == null || value == '' ? $(this).find('.placeholder').data('placeholder') : value;
        $(this).parent('.file-wrapper').find('.placeholder').text(value);
    });

    var onChange = function(obj) {

        var style = $(obj.selEl).find('li.cs-selected').attr('style');


        $(obj.selPlaceholder).attr('style',style);
    };
    });

