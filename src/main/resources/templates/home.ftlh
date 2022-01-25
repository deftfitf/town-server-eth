<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Town</title>
  <link rel="stylesheet" type="text/css" href="/public/css/style.css">
</head>
<body>
<h1>Town</h1>
<div id="content">
  <table id="town-field">
    <tbody>
    <#list model["town"].fields as line>
      <tr>
          <#list line as land>
            <td class="town-land town-${land.getType()}">
            </td>
          </#list>
      </tr>
    </#list>
    </tbody>
  </table>
</div>
</body>
</html>