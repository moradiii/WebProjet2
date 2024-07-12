<%@ page language="java" contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
    
<%@ page import="java.util.List" %>
<%@ page import="Model.CourrierA" %>

<%

	// first test 
	if(request.getAttribute("listUtilist") != null ){
		List m = (List)request.getAttribute("listUtilist");
		System.out.println(" liste size ds jsp ===> " + m.size());
	}else{
		System.out.println(" la lsite est vide dans la jsp");
	}

%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Table</title>
	<link rel="icon" href="img\Logo.png" type="image/png">
	<script type="text/javascript" src="https://unpkg.com/xlsx@0.15.1/dist/xlsx.full.min.js"></script>
    <style>
        table {
            border-collapse: collapse;
            width: 100%;
        }

        th, td {
            border: 2px solid black;
	        padding: 8px;
	        text-align: left;
        }   
		    
		th {
	   	    background-color: #333;
	  	    color: rgb(206,177,114);
	    }
		
        tr:nth-child(even) {
            background-color: #bfbfbf;
        }
        
        .filter {
       	    padding: 10px;
		    font-size: 17px;
		    border: 1px solid #333;
		    border-radius: 25px;
		    float: left;
		    width: 20%;
		    padding-left: 25px;
		    background: white;
		    border-top-right-radius: 0;
		    border-bottom-right-radius: 0;
		    border-right: 0;
		    border-right: 1px solid #333;
		    outline: 0
        }
        
        .button {
       		border-radius: 25px;
		    float: left;
		    width: 5%;
		    padding: 10px;
		    background: #333;
		    color: rgb(206,177,114);
		    font-size: 17px;
		    border: 1px solid #333;
		    border-left: none;
		    cursor: pointer;
		    border-top-left-radius: 0;
		    border-bottom-left-radius: 0;
        }
        
        .button:hover, .list:hover, .update:hover , .delete:hover, .insert:hover, .export:hover {
        	box-shadow: 0 12px 16px 0 rgba(0,0,0,0.24),0 17px 50px 0 rgba(0,0,0,0.19);
        }
        
        .list {
        	border-radius: 25px;
		    float: left;
		    width: 5%;
		    padding: 10px;
		    background: #333;
		    color: rgb(206,177,114);
		    font-size: 17px;
		    border: 1px solid #333;
		    border-left: none;
		    cursor: pointer;
        }
        
        .export {
        	border-radius: 25px;
		    float: left;
		    width: 10%;
		    padding: 10px;
		    background: #333;
		    color: rgb(206,177,114);
		    font-size: 17px;
		    border: 1px solid #333;
		    border-left: none;
		    cursor: pointer;
        }
        
        #content{
        margin-bottom: 70px;
        }
        
        
    </style>
</head>

<body style="background-color: #ebe3db;">
<jsp:include page="Nav.jsp" />
<div id="content" style="margin-top: 70px;">
	<table id="first" style="background-color: whitesmoke;">
        <tr>
            <th>Action</th>
            <th>ID</th>
            <th>Utilisateurs</th>
            <th>Nom de machine</th>
            <th>Designation</th>
            <th>SN / TAG</th>
            <th>Type</th>
            <th>OS</th>
            <th>OS Status</th>
            <th>Protection Status</th>
            <th>Office installé</th>
            <th>Configuration</th>
            <th>Moniteur SN / TAG</th>
            <th>Fournisseurs</th>
            <th>Date d'achat PC</th>
            <th>Date d'achat ecran</th>
            <th>Observation</th>
        </tr>
        
        <%-- Iterate over the listUtilist and display each row --%>
        <c:forEach items="${listUtilist}" var="utilist">
            <tr> 
            <form action="<%= request.getContextPath() %>/list" method="post">
            	<td>
            		<center>
					    <input type=hidden name="act" value="update"> <!-- Add a hidden field to indicate the action -->
					    <input type="hidden" name="id" value="${utilist.id}"></input>
						<button class="update" type="submit" value="update"> Update</button>
						<br><br>
                        <button class="delete" type="submit"><a href="delete?id=<c:out value='${utilist.id}' />">Delete</a></button>
                </td>
                <td style="text-align:center;background-color: rgb(206,177,114);color: #333;">${utilist.id}</td>
                <td><input type="text" name="Utilisateurs" value="${utilist.getUtilisateurs()}"></td>
                <td><input type="text" name="NomMachine" value="${utilist.getNomMachine()}"></td>
                <td><input type="text" name="Designation" value="${utilist.getDesignation()}"></td>
                <td><input type="text" name="SNTAG" value="${utilist.getSNTAG()}"></td>
                <td>
                	<select name="Type">
                		<option value="${utilist.getType()}">${utilist.getType()}</option>
                		<option value="Portable">Portable</option>
                		<option value="Desktop">Desktop</option>
                		<option value="mobile">Mobile</option>
                		<option value="tablet">Tablet</option>
                	</select>
                </td>
                <td><input type="text" name="OS" value="${utilist.getOS()}"></td>
                <td>
                	<select name="OsStatus">
                		<option value="${utilist.getOsStatus()}">${utilist.getOsStatus()}</option>
                		<option value="enabled">Enabled</option>
                		<option value="disabled">Disabled</option>
                	</select>
                </td>
                <td>
                	<select name="ProtectionStatus">
                		<option value="${utilist.getProtectionStatus()}">${utilist.getProtectionStatus()}</option>
                		<option value="enabled">Enabled</option>
                		<option value="disabled">Disabled</option>
                	</select>
                </td>
                <td>
	            	<select name="OfficeInsatlle">
	            		<option value="${utilist.getOfficeInsatlle()}">${utilist.getOfficeInsatlle()}</option>
	            		<option value="Yes">Yes</option>
	            		<option value="Yes">No</option>
	            	</select>
	            </td>
                <td><input type="text" name="Configuration" value="${utilist.getConfiguration()}"></td>
                <td><input type="text" name="MoniteurSnTag" value="${utilist.getMoniteurSnTag()}"></td>
                <td><input type="text" name="Fournisseurs" value="${utilist.getFournisseurs()}"></td>
                <td><input type="text" name="DateAchatPC" value="${utilist.getDateAchatPC()}"></td>
                <td><input type="text" name="DateAchatEcran" value="${utilist.getDateAchatEcran()}"></td>
                <td>
                	<textarea name="Observation" rows="3" cols="20">${utilist.getObservation()}</textarea>
                </td>
                </form>
            </tr>
        </c:forEach>
        
        
    </table>
    <br>
    <br>
        <table>
        <tr>
            <th>Action</th>
            <th>ID</th>
            <th>Utilisateurs</th>
            <th>Nom de machine</th>
            <th>Designation</th>
            <th>SN / TAG</th>
            <th>Type</th>
            <th>OS</th>
            <th>OS Status</th>
            <th>Protection Status</th>
            <th>Office installé</th>
            <th>Configuration</th>
            <th>Moniteur SN / TAG</th>
            <th>Fournisseurs</th>
            <th>Date d'achat PC</th>
            <th>Date d'achat ecran</th>
            <th>Observation</th>
        </tr>
        <tr>
        	<form action="<%= request.getContextPath() %>/insert" method="post">
    <td>
        <input class="insert" type="submit" value="Insert">
    </td>
    <td style="text-align:center;background-color: rgb(206,177,114);color: #333;"></td>
    <td><input type="text" name="Utilisateurs" value=""></td>
    <td><input type="text" name="NomMachine" value=""></td>
    <td><input type="text" name="Designation" value=""></td>
    <td><input type="text" name="SNTAG" value=""></td>
    <td>
        <select name="Type">
        	<option value=""></option>
            <option value="Portable">Portable</option>
            <option value="Desktop">Desktop</option>
            <option value="mobile">Mobile</option>
            <option value="tablet">Tablet</option>
        </select>
    </td>
    <td><input type="text" name="OS" value=""></td>
    <td>
        <select name="OsStatus">
        	<option value=""></option>
            <option value="enabled">Enabled</option>
            <option value="disabled">Disabled</option>
        </select>
    </td>
    <td>
        <select name="ProtectionStatus">
        	<option value=""></option>
            <option value="enabled">Enabled</option>
            <option value="disabled">Disabled</option>
        </select>
    </td>
    <td>
        <select name="OfficeInsatlle">
        	<option value=""></option>
            <option value="Yes">Yes</option>
            <option value="No">No</option>
        </select>
    </td>
    <td><input type="text" name="Configuration" value=""></td>
    <td><input type="text" name="MoniteurSnTag" value=""></td>
    <td><input type="text" name="Fournisseurs" value=""></td>
    <td><input type="text" name="DateAchatPC" value=""></td>
    <td><input type="text" name="DateAchatEcran" value=""></td>
    <td>
        <textarea name="Observation" rows="3" cols="20"></textarea>
    </td>
</form>

		</tr>
    </table>
    <br>
        <input class="filter" type="text" name="id" id="filterId" placeholder="Enter ID..">
        <button class="button" type="button" onclick="filterTable()">Filter</button>
        <br><br><br>
       <form action="<%= request.getContextPath() %>/list" method="post">
           <button class="list" type="submit" name="action" value="list">List</button>
       </form>
		<br><br><br>
    	<button class="export" onclick="ExportToExcel('xlsx')">Export to Excel</button>
		
    <script>
        function filterTable() {
            const filterId = document.getElementById('filterId').value;
            const table = document.getElementById('first');
            const rows = table.getElementsByTagName('tr');

            // Loop through each row of the table and hide all rows that don't match the filter ID
            for (let i = 1; i < rows.length; i++) {
                const cell = rows[i].getElementsByTagName('td')[1]; // Assuming the ID is in the second column (index 1)
                const id = cell.textContent || cell.innerText; // Get the ID value

                if (id === filterId) {
                    rows[i].style.display = ''; // Show the matching row
                } else {
                    rows[i].style.display = 'none'; // Hide non-matching rows
                }
            }
        }
    </script>
    
     <!-- Place this script after the previous script in the JSP file -->

<script>
  function ExportToExcel(type, fn, dl) {
    const mainTable = document.getElementById('first');

    // Create a new table to be used for export
    const exportTable = document.createElement('table');

    // Create the header row for the export table
    const headerRow = document.createElement('tr');

    // Define the column headers to be exported
    const columnHeaders = [
      'ID', 'Utilisateurs', 'Nom de machine', 'Designation', 'SN / TAG',
      'Type', 'OS', 'OS Status', 'Protection Status', 'Office installé',
      'Configuration', 'Moniteur SN / TAG', 'Fournisseurs', 'Date d\'achat PC',
      'Date d\'achat ecran', 'Observation'
    ];

    // Add the column headers to the export table
    for (const columnHeader of columnHeaders) {
      const th = document.createElement('th');
      th.innerText = columnHeader;
      headerRow.appendChild(th);
    }

    exportTable.appendChild(headerRow);

    // Get the rows from the main table and add the desired columns to the export table
    const rows = mainTable.getElementsByTagName('tr');
    for (let i = 1; i < rows.length; i++) { // Start from 1 to skip the header row
      const cells = rows[i].getElementsByTagName('td');
      if (cells.length >= columnHeaders.length) { // Make sure the row has enough cells for the desired columns
        const newRow = document.createElement('tr');
        for (let j = 1; j <= columnHeaders.length; j++) { // Start from 1 to skip the action column
          const td = document.createElement('td');
          const cellContent = cells[j].getElementsByTagName('input').length > 0 // Check if the cell contains an input element (for <input> fields)
            ? cells[j].getElementsByTagName('input')[0].value
            : cells[j].getElementsByTagName('select').length > 0 // Check if the cell contains a select element (for <select> fields)
              ? cells[j].getElementsByTagName('select')[0].value
              : cells[j].innerText; // Use the cell's content as is for other types of data
          td.innerText = cellContent;
          newRow.appendChild(td);
        }
        exportTable.appendChild(newRow);
      }
    }

    // Convert the export table to a workbook and download it
    const wb = XLSX.utils.table_to_book(exportTable, { sheet: 'sheet1' });
    return dl
      ? XLSX.write(wb, { bookType: type, bookSST: true, type: 'base64' })
      : XLSX.writeFile(wb, fn || ('TableA.' + (type || 'xlsx')));
  }
</script>
     
</div>
</body>
</html>
