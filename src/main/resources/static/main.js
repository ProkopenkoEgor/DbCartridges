$('document').ready(function () {

    $('.table #deleteButton').on('click',function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        $('#deleteModal #delRef').attr('href', href);
        $('#deleteModal').modal();
    });

    //поиск по таблице в реальном времени
    //     $("#search").keyup(function(){
    //         _this = this;
    //
    //         $.each($("#mytable tbody tr"), function() {
    //             if($(this).text().toLowerCase().indexOf($(_this).val().toLowerCase()) === -1) {
    //                 $(this).hide();
    //             } else {
    //                 $(this).show();
    //             });
    //     });
})