$(document).ready(function () {
        $("#addOrg").click(function () {
            console.log('ORGANIZATION');
            var org = {
                name: $("#name").val(),
                fullName: $("#fullName").val(),
                inn: $("#inn").val(),
                kpp: $("#kpp").val(),
                address: $("#address").val(),
                phone: $("#phone").val(),
                isActive: $("#isActive").val() == 1 ? true : false
            };

            console.log('ORG', org);

            $.ajax({
                url:"/api/organization/save",
                type:"POST",
                data: JSON.stringify(org),
                contentType:"application/json; charset=utf-8",
                success: function(result){
                    console.log(result);
                    alert(result);
                }
            });

        });



        $("#updOrg").click(function () {
            console.log('ORGANIZATION UPDATE');
            var org = {
                id: $("#id").val(),
                name: $("#name").val(),
                fullName: $("#fullName").val(),
                inn: $("#inn").val(),
                kpp: $("#kpp").val(),
                address: $("#address").val(),
                phone: $("#phone").val(),
                isActive: $("#isActive").prop( "checked" )
            };

            console.log('ORG', org);

            $.ajax({
                url:"/api/organization/update",
                type:"POST",
                data: JSON.stringify(org),
                contentType:"application/json; charset=utf-8",
                success: function(result){
                    console.log(result);
                    alert(result);
                }
            });

        });



        $("#listOrg").click(function () {
            console.log('ORGANIZATION LIST');
            var org = {
                name: $("#name").val(),
                inn: $("#inn").val(),
                isActive: $("#isActive").val() == 1 ? true : false
            };

            console.log('ORG', org);

            $.ajax({
                url:"/api/organization/list",
                type:"POST",
                data: JSON.stringify(org),
                contentType:"application/json; charset=utf-8",
                success: function(result){
                    console.log(result);
                    alert(result);
                }
            });

        });


        $("#delOrg").click(function () {
            console.log('ORGANIZATION DELETE');
            var org = {
                id: $("#id").val()
            };

            console.log('ORG', org);

            $.ajax({
                url:"/api/organization/list",
                url:"/api/organization/delete",
                type:"POST",
                data: JSON.stringify(org),
                contentType:"application/json; charset=utf-8",
                success: function(result){
                    console.log(result);
                    alert(result);
                }
            });

        });




});
