$(document).ready(function () {
        $("#navOrg").click(function () {
            $("#organization").show();
            $("#office").hide();
            $("#user").hide();
        });
        $("#navOffice").click(function () {
            $("#office").show();
            $("#organization").hide();
            $("#user").hide();
        });
        $("#navUser").click(function () {
            $("#user").show();
            $("#office").hide();
            $("#organization").hide();
        });



        $("#addOrg").click(function () {
            console.log('ORGANIZATION');
            var org = {
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
                isActive: $("#isActive").prop( "checked" )
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

/*---------------------------------------------------------------------------------------*/


        $("#addOf").click(function () {
            console.log('OFFICE');
            var ofi = {
                orgId: $("#idOrgOf").val(),
                name: $("#nameOf").val(),
                address: $("#addressOf").val(),
                phone: $("#phoneOf").val(),
                isActive: $("#isActiveOf").prop( "checked" )
            };

            console.log('OFI', ofi);

            $.ajax({
                url:"/api/office/save",
                type:"POST",
                data: JSON.stringify(ofi),
                contentType:"application/json; charset=utf-8",
                success: function(result){
                    console.log(result);
                    alert(result);
                }
            });

        });

        $("#updOf").click(function () {
            console.log('OFFICE UPDATE');
            var ofi = {
                id: $("#idOf").val(),
                orgId: $("#idOrgOf").val(),
                name: $("#nameOf").val(),
                address: $("#addressOf").val(),
                phone: $("#phoneOf").val(),
                isActive: $("#isActiveOf").prop( "checked" )
            };

            console.log('OFI', ofi);

            $.ajax({
                url:"/api/office/update",
                type:"POST",
                data: JSON.stringify(ofi),
                contentType:"application/json; charset=utf-8",
                success: function(result){
                    console.log(result);
                    alert(result);
                }
            });

        });



        $("#listOf").click(function () {
            console.log('OFFICE LIST');
            var ofi = {
                orgId: $("#idOrgOf").val(),
                name: $("#nameOf").val(),
                inn: $("#innOf").val(),
                isActive: $("#isActiveOf").prop( "checked" )
            };

            console.log('OFI', ofi);

            $.ajax({
                url:"/api/office/list",
                type:"POST",
                data: JSON.stringify(ofi),
                contentType:"application/json; charset=utf-8",
                success: function(result){
                    console.log(result);
                    alert(result);
                }
            });

        });


        $("#delOf").click(function () {
            console.log('OFFICE DELETE');
            var ofi = {
                id: $("#idOf").val()
            };

            console.log('OFI', ofi);

            $.ajax({
                url:"/api/office/delete",
                type:"POST",
                data: JSON.stringify(ofi),
                contentType:"application/json; charset=utf-8",
                success: function(result){
                    console.log(result);
                    alert(result);
                }
            });

        });


/*-------------------------------------------------------------------------------------*/

        $("#addUs").click(function () {
            console.log('USER');
            var us = {
                firstName: $("#fnameUs").val(),
                secondName: $("#snameUs").val(),
                middleName: $("#mnameUs").val(),
                position: $("#positionUs").val(),
                phone: $("#phoneUs").val(),
                docCode: $("#docCode").val(),
                docName: $("#docnameUs").val(),
                docNumber: $("#docnumberUs").val(),
                docDate: $("#docdateUs").val(),
                citizenshipName: $("#citizenshipNameUs").val(),
                citizenshipCode: $("#citizenshipCodeUs").val(),
                isIdentified: $("#isIdentUs").prop( "checked" )
            };

            console.log('US', us);

            $.ajax({
                url:"/api/user/save",
                type:"POST",
                data: JSON.stringify(us),
                contentType:"application/json; charset=utf-8",
                success: function(result){
                    console.log(result);
                    alert(result);
                }
            });
        });


        $("#updUs").click(function () {
            console.log('USER UPDATE');
            var us = {
                id: $("#idUs").val(),
                firstName: $("#fnameUs").val(),
                secondName: $("#snameUs").val(),
                middleName: $("#mnameUs").val(),
                position: $("#positionUs").val(),
                phone: $("#phoneUs").val(),
                docName: $("#docnameUs").val(),
                docNumber: $("#docnumberUs").val(),
                docDate: $("#docdateUs").val(),
                citizenshipName: $("#citizenshipNameUs").val(),
                citizenshipCode: $("#citizenshipCodeUs").val(),
                isIdentified: $("#isIdentUs").prop( "checked" )
            };

            console.log('US', us);

            $.ajax({
                url:"/api/user/update",
                type:"POST",
                data: JSON.stringify(us),
                contentType:"application/json; charset=utf-8",
                success: function(result){
                    console.log(result);
                    alert(result);
                }
            });

        });


        $("#listUs").click(function () {
            console.log('USER LIST');
            var us = {
                officeId: $("#idOfUs").val(),
                firstName: $("#fnameUs").val(),
                secondName: $("#snameUs").val(),
                middleName: $("#mnameUs").val(),
                position: $("#positionUs").val(),
                docCode: $("#docCode").val(),
                citizenshipCode: $("#citizenshipCodeUs").val()
            };

            console.log('US', us);

            $.ajax({
                url:"/api/user/list",
                type:"POST",
                data: JSON.stringify(us),
                contentType:"application/json; charset=utf-8",
                success: function(result){
                    console.log(result);
                    alert(result);
                }
            });

        });


        $("#delUs").click(function () {
            console.log('USER DELETE');
            var us = {
                id: $("#idUs").val()
            };

            console.log('US', us);

            $.ajax({
                url:"/api/user/delete",
                type:"POST",
                data: JSON.stringify(us),
                contentType:"application/json; charset=utf-8",
                success: function(result){
                    console.log(result);
                    alert(result);
                }
            });

        });
        

});
