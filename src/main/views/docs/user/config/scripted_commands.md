---
Author: Chris Ellis
Date: 2017-07-25
Code: javascript
---
# Scripted Commands

Bergamot Monitoring has a number of differing check workers, all targeted for specialised to rexecute different types of checks.  Most of these workers can be scripted using Javascript, this allows a vast range of check commands to be implemented using nothing but configuration, avoiding the need to write and distribute a Nagios style check executable.

## Supported Workers

The following workers support scripted commands:

* HTTP
* JDBC
* SSH
* JMX
* SNMP

## Configuring A Scripted Command

Below is an example of a scripted command which is executed by the JDBC worker.  This example connects to a PostgreSQL database and gets the version number.

    <command name="postgresql_version" extends="postgresql_jdbc_check">
        <summary>PostgreSQL Version</summary>
        <script>
        <![CDATA[
            /* Validate parameters */
            bergamot.require('host');
            bergamot.require('port');
            bergamot.require('database');
            bergamot.require('username');
            bergamot.require('password');
            /* URL */
            var url = 'jdbc:postgresql://' + check.getParameter('host') + ':' + check.getIntParameter('port') + '/' + check.getParameter('database');
            var user = check.getParameter('username');
            var pass = check.getParameter('password');
            /* Execute */
            jdbc.connect(url, user, pass, function(con) {
                con.query('SELECT setting FROM pg_settings WHERE name = \'server_version\'', function(rs) {
                    rs.next();
                    bergamot.info("PostgreSQL " + rs.getString(1));
                });
            });
        ]]>
        </script>
        <description>PostgreSQL version information</description>
    </command>

The `script` element defines the Javascript script which gets executed by the worker, it's best to place the script in a `CDATA` section to avoid escaping irritations.

### JDBC Scripted Commands

When defining a scripted JDBC command, you should extend one of the following commands:

* `postgresql_jdbc_check` for a PostgreSQL targeted command
* `jdbc_script_check` for a generic JDBC targeted command

### HTTP Scripted Commands

When defining a scripted HTTP or HTTPS command, you should extend one of the following commands:

* `http_script_check` for a generic HTTP/HTTPS command

### SSH Scripted Commands

When defining a scripted SSH or SFTP command, you should extend one of the following commands:

* `ssh_script_check` for a generic SSH command
* `sftp_script_check` for a generic SFTP command

### JMX Scripted Commands

When defining a scripted JMX command, you should extend one of the following commands:

* `jmx_script_check` for a generic JMX command

### SNMP Scripted Commands

When defining a scripted SNMP command, you should extend one of the following commands:

* `snmp_check` for a generic SNMP command

## Getting Check Details

A global variable named `check` is exposed to the command script.  This provides access to information about the check to execute, mainly providing access to check parameters.

You can use the following functions to get a parameter value, optionally providing a default value:

    String value  = check.getParameter(String name);
    String value  = check.getParameter(String name, String defaultValue);

Should you want to check if a parameter exists:

    boolean exists = check.containsParameter(String name);

You can also find all parameter values which start with a specific prefix:

    List<String> values = getParametersStartingWithValues(String prefix);
    
If you need to get a numeric parameter value:

    int     value = getIntParameter(String name);
    int     value = getIntParameter(String name, int defaultValue);
    long    value = getLongParameter(String name);
    long    value = getLongParameter(String name, long defaultValue);
    double  value = getDoubleParameter(String name);
    double  value = getDoubleParameter(String name, double defaultValue);
    float   value = getFloatParameter(String name);
    float   value = getFloatParameter(String name, float defaultValue);

If you want to get a boolean parameter value, where: `true`, `on` or `yes` are `TRUE` and all other values are `FALSE`:

    boolean value = getBooleanParameter(String name);
    boolean value = getBooleanParameter(String name, boolean defaultValue);

If you want to get a percentage parameter value, which is then normalised to between 0 and 1:

    double value = getPercentParameter(String name);
    double value = getPercentParameter(String name, double defaultValue);

if you want a parameter value which is a list (separated by `,`):

    OrderSet<String> value = getParameterCSV(String name);
    OrderSet<String> value = getParameterCSV(String name, String defaultValue);

If you want a parameter value which is a numeric list (separated by `,`):

    OrderSet<Integer> value = getIntParameterCSV(String name);
    OrderSet<Integer> value = getIntParameterCSV(String name, String defaultValue);
    OrderSet<Long>    value = getLongParameterCSV(String name);
    OrderSet<Long>    value = getLongParameterCSV(String name, String defaultValue);
    OrderSet<Double>  value = getDoubleParameterCSV(String name);
    OrderSet<Double>  value = getDoubleParameterCSV(String name, String defaultValue);
    OrderSet<Float>   value = getFloatParameterCSV(String name);
    OrderSet<Float>   value = getFloatParameterCSV(String name, String defaultValue);
    
If you want to get a numeric range parameter value (in the form `lower:upper`):
    
    Integer[] value = getIntRangeParameter(String name, String defaultValue);
    Long[]    value = getLongRangeParameter(String name, String defaultValue);
    Double[]  value = getDoubleRangeParameter(String name, String defaultValue);
    Float[]   value = getFloatRangeParameter(String name, String defaultValue);

## Validating Check Parameters

A global variable named `bergamot` is exposed to the command script.  This provides access to various Bergamot Monitoring APIs.  You can use this to validate a check parameter has been defined using:

    bergamot.require('<parameter_name>');

This will assert that a parameter is degined, generating an error result if the parameter is undefined.

## Publishing Results

Command scripts run asynchronously, when they have determind the state of a check a result needs to be published.  The `bergamot` global variable exposes the API to publish results, this API has a number of helper functions to make publishing results as simple as possible.  It is important that a command script only published one result.

### Publishing A Result

To publish simple results, you can use the following:

    bergamot.info(String checkOutput);
    bergamot.ok(String checkOutput);
    bergamot.warning(String checkOutput);
    bergamot.critical(String checkOutput);
    bergamot.unknown(String checkOutput);
    bergamot.error(String checkOutput);
    bergamot.timeout(String checkOutput);
    bergamot.disconnected(String checkOutput);
    bergamot.action(String checkOutput);

Where each function corresponds to the status of the check.

### Applying Thresholds

Most checks want to apply a `warning` and `critical` threshold to a check, there are a number of functions exposed to make this as simple as possible.  You can apply simple threshold checks easily if your check uses the standardised `warning` and `critical` parameter names using the following functions:

    bergamot.applyGreaterThanThreshold(Double value, String message);
    bergamot.applyGreaterThanThreshold(Float value, String message);
    bergamot.applyGreaterThanThreshold(Long value, String message);
    bergamot.applyGreaterThanThreshold(Integer value, String message);
    
    bergamot.applyLessThanThreshold(Double value, String message);
    bergamot.applyLessThanThreshold(Float value, String message);
    bergamot.applyLessThanThreshold(Long value, String message);
    bergamot.applyLessThanThreshold(Integer value, String message);

If you are not using the standardised parameter names, or wish to apply computation to the warning and critical thresholds first, then you can use the following functions:

The following functions will check if the value is greater than the warning or critical threshold and publish the appropriate result:

    bergamot.applyGreaterThanThreshold(Double value, Double warning, Double critical, String message);
    bergamot.applyGreaterThanThreshold(Float value, Float warning, Float critical, String message);
    bergamot.applyGreaterThanThreshold(Long value, Long warning, Long critical, String message);
    bergamot.applyGreaterThanThreshold(Integer value, Integer warning, Integer critical, String message);

The following functions will check if the value is less than the warning or critical thresholds and publish the appropriate result:
    
    bergamot.applyLessThanThreshold(Double value, Double warning, Double critical, String message);
    bergamot.applyLessThanThreshold(Float value, Float warning, Float critical, String message);
    bergamot.applyLessThanThreshold(Long value, Long warning, Long critical, String message);
    bergamot.applyLessThanThreshold(Integer value, Integer warning, Integer critical, String message);

Sometimes you want to apply a threshold to an array of values, for example if you wanted to check the free disk space of all partitions.

The following functions will check if any of the values are greater than the warning or critical threshold and publish the appropriate result:
    
    bergamot.applyGreaterThanThresholds(Iterable<Double> values, Double warning, Double critical, String message);
    bergamot.applyGreaterThanThresholds(Iterable<Float> values, Float warning, Float critical, String message);
    bergamot.applyGreaterThanThresholds(Iterable<Long> values, Long warning, Long critical, String message);
    bergamot.applyGreaterThanThresholds(Iterable<Integer> values, Integer warning, Integer critical, String message);

The following functions will check if any of the values are less than the warning or critical threshold and publish the appropriate result:
    
    bergamot.applyLessThanThresholds(Iterable<Double> values, Double warning, Double critical, String message)
    bergamot.applyLessThanThresholds(Iterable<Float> values, Float warning, Float critical, String message);
    bergamot.applyLessThanThresholds(Iterable<Long> values, Long warning, Long critical, String message);
    bergamot.applyLessThanThresholds(Iterable<Integer> values, Integer warning, Integer critical, String message);

### Advanced Result Publihsing

You can use the function `bergamot.publish( result );` to submit a result object.  Using this approach requires that you first create a result object using `var result = bergamot.createResult();`, this then allows full access to the result API to leverage the more advanced features of Bergamot Monitoring.

## Publishing Readings

You can pubish metric readings to Bergamot Monitoring, this allows Bergamot Monitoring to record and display graphs of check performance metrics, which are really handy for seeing trends in systems.  The easiest way to publish readings is using the `bergamot.publishReadings()` function, this takes an array of objects defining the readings, for example:

    bergamot.publishReadings([
        { type: 'long-gauge',   name: 'used-disk-space', unit: 'MB', value: 153023, warning: 100000, critical: 200000, min: null, max: null },
        { type: 'double-gauge', name: 'free-memory',     unit: '%',  value: 73.242, warning: null,   critical: null,   min: 0,    max: 100  }
    ]);

The `warning`, `critical`, `min`, `max` properties are optional.  The `unit` property can be `null` or `''` if the reading is dimensionless.  The `type`, `name` and `value` properties must be provided for the reading to be published.

The following reading types are currently supported:

* `double-gauge`
* `long-gauge`
* `float-gauge`
* `int-gauge`

## Scripting HTTP Checks

## Scripting JDBC Checks

## Scripting SSH Checks

## Scripting SFTP Checks

## Scripting JMX Checks

## Scripting SNMP Checks
