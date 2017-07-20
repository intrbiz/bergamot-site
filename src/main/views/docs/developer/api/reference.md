---
Author: Chris Ellis
Date: 2017-06-25
Code: bash
---
# Bergamot Monitoring API Reference

* [Authentication API Methods](#apirouter)
* [Alert API Methods](#alertsapirouter)
* [Host API Methods](#hostapirouter)
* [LocationAPIRouter](#locationapirouter)
* [GroupAPIRouter](#groupapirouter)
* [ClusterAPIRouter](#clusterapirouter)
* [Service API Methods](#serviceapirouter)
* [TrapAPIRouter](#trapapirouter)
* [ResourceAPIRouter](#resourceapirouter)
* [TimePeriodAPIRouter](#timeperiodapirouter)
* [CommandAPIRouter](#commandapirouter)
* [ContactAPIRouter](#contactapirouter)
* [TeamAPIRouter](#teamapirouter)
* [CommentsAPIRouter](#commentsapirouter)
* [DowntimeAPIRouter](#downtimeapirouter)
* [ConfigAPIRouter](#configapirouter)
* [Stats API Methods](#statsapirouter)
* [UtilAPIRouter](#utilapirouter)
* [Lamplighter (Readings) API Methods](#lamplighterapirouter)

<p><a id="apirouter"></a></p>
## Authentication API Methods

### handleCORSPreflight

`OPTIONS` `/api**`

### Generate temporary authentication token

`GET` `/api/auth-token`

Temporary authentication tokens last for 1 hour from creation and can be used to authorize subsequent requests to the Bergamot Monitoring API wit the same level of access as requestor.

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/auth-token

<p><a id="alertsapirouter"></a></p>
## Alert API Methods

Alerts are raised when a check has reached a steady not OK state and someone or something needs to be notified.

### List alerts

`GET` `/api/alert/`

Get the list of all currently active alerts, returning minimal information about each alert.

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/alert/

### Get alert

`GET` `/api/alert/id/:id`

Get alert identified by the given UUID

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/alert/id/:id

### Get alerts for check

`GET` `/api/alert/for-check/id/:id`

Get the alerts for the given check identified by the given UUID.

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/alert/for-check/id/:id

### Get current alert for check

`GET` `/api/alert/current/for-check/id/:id`

Get the current alert for the given check identified by the given UUID.

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/alert/current/for-check/id/:id

### Acknowledge alert

`ANY` `/api/alert/id/:id/acknowledge`

Acknowledge the alert identified by the given UUID with the given summary and comment

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `summary` (type: String) (provided as a query parameter)
* `comment` (type: String) (provided as a query parameter)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/alert/id/:id/acknowledge?summary=&comment=

<p><a id="hostapirouter"></a></p>
## Host API Methods

Hosts represent pysical and virtual servers upon which Services run.  These API calls provide information on configured Host checks and their current state.

### Get host

`GET` `/api/host/name/:name`

Get the host check for the given name, returning minimal information about the host check.

#### Parameters

* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/name/:name

### Execute a host check

`GET` `/api/host/id/:id/execute`

Execute a check immediately for the host identified by the given UUID.

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/execute

### Execute all service checks on a host

`GET` `/api/host/id/:id/execute-services`

Execute a check immediately for every service on the host identified by the given UUID.

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/execute-services

### Suppress all services on a host

`GET` `/api/host/id/:id/suppress-services`

Suppress all services on the host identified by the given UUID.

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/suppress-services

### Unsuppress all services on a host

`GET` `/api/host/id/:id/unsuppress-services`

Unsuppress all services on the host identified by the given UUID.

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/unsuppress-services

### List hosts

`GET` `/api/host/`

Retreive the list of all hosts for this site, with minimal information for each host.

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/

### Suppress a host

`GET` `/api/host/id/:id/suppress`

Suppress the host identified by the given UUID, this will prevent any alerts being raised for this host.

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/suppress

### Unsuppress a host

`GET` `/api/host/id/:id/unsuppress`

Unsuppress the host identified by the given UUID, this will stop preventing any alerts being raised for this host.

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/unsuppress

### Get host state

`GET` `/api/host/name/:name/state`

Get the state of the host check for the given name, returning just the check state.

#### Parameters

* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/name/:name/state

### Get host state

`GET` `/api/host/id/:id/state`

Get the state of the host check for the given id (UUID), returning just the check state.

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/state

### Get services on host

`GET` `/api/host/name/:name/services`

Get all the services on the host identified by the given host name.

#### Parameters

* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/name/:name/services

### Get services on host

`GET` `/api/host/id/:id/services`

Get all the services on the host identified by the given UUID.

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/services

### Get traps on host

`GET` `/api/host/name/:name/traps`

Get all the traps on the host identified by the given host name.

#### Parameters

* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/name/:name/traps

### Get traps on host

`GET` `/api/host/id/:id/traps`

Get all the traps on the host identified by the given UUID.

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/traps

### Suppress all traps on a host

`GET` `/api/host/id/:id/suppress-traps`

Suppress all traps on the host identified by the given UUID.

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/suppress-traps

### Unsuppress all traps on a host

`GET` `/api/host/id/:id/unsuppress-traps`

Unsuppress all traps on the host identified by the given UUID.

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/unsuppress-traps

### Suppress all services and traps on a host

`GET` `/api/host/id/:id/suppress-all`

Suppress all services and traps on the host identified by the given UUID.

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/suppress-all

### Unsuppress all services and traps on a host

`GET` `/api/host/id/:id/unsuppress-all`

Unsuppress all services and traps on the host identified by the given UUID.

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/unsuppress-all

### Get host configuration

`GET` `/api/host/name/:name/config.xml`

Get the configuration for the host identified by the given name, returning the XML configuration snippet.

#### Parameters

* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/name/:name/config.xml

### Get host configuration

`GET` `/api/host/id/:id/config.xml`

Get the configuration for the host identified by the given UUID, returning the XML configuration snippet.

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/config.xml

### Get host

`GET` `/api/host/id/:id`

Get the host check for the given id (UUID), returning minimal information about the host check.

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id

<p><a id="locationapirouter"></a></p>
## LocationAPIRouter

### getRootLocations

`GET` `/api/location/roots`

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/location/roots

### getLocationByName

`GET` `/api/location/name/:name`

#### Parameters

* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/location/name/:name

### executeHostsInLocation

`GET` `/api/location/id/:id/execute-all-hosts`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/location/id/:id/execute-all-hosts

### getLocations

`GET` `/api/location/`

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/location/

### getLocationChildrenByName

`GET` `/api/location/name/:name/children`

#### Parameters

* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/location/name/:name/children

### getLocationHostsByName

`GET` `/api/location/name/:name/hosts`

#### Parameters

* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/location/name/:name/hosts

### getLocationChildren

`GET` `/api/location/id/:id/children`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/location/id/:id/children

### getLocationHosts

`GET` `/api/location/id/:id/hosts`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/location/id/:id/hosts

### getLocation

`GET` `/api/location/id/:id`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/location/id/:id

<p><a id="groupapirouter"></a></p>
## GroupAPIRouter

### getRootGroups

`GET` `/api/group/roots`

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/group/roots

### getGroupByName

`GET` `/api/group/name/:name`

#### Parameters

* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/group/name/:name

### getGroup

`GET` `/api/group/id/:id`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/group/id/:id

### executeChecksInGroup

`GET` `/api/group/id/:id/execute-all-checks`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/group/id/:id/execute-all-checks

### getGroups

`GET` `/api/group/`

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/group/

### getGroupChildren

`GET` `/api/group/id/:id/children`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/group/id/:id/children

### getGroupChecks

`GET` `/api/group/id/:id/checks`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/group/id/:id/checks

### getGroupChildrenByName

`GET` `/api/group/name/:name/children`

#### Parameters

* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/group/name/:name/children

### getGroupChecksByName

`GET` `/api/group/name/:name/checks`

#### Parameters

* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/group/name/:name/checks

<p><a id="clusterapirouter"></a></p>
## ClusterAPIRouter

### getClusterByName

`GET` `/api/cluster/name/:name`

#### Parameters

* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/cluster/name/:name

### getCluster

`GET` `/api/cluster/id/:id`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/cluster/id/:id

### suppressCluster

`GET` `/api/cluster/id/:id/suppress`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/cluster/id/:id/suppress

### unsuppressCluster

`GET` `/api/cluster/id/:id/unsuppress`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/cluster/id/:id/unsuppress

### getClusters

`GET` `/api/cluster/`

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/cluster/

### getClusterStateByName

`GET` `/api/cluster/name/:name/state`

#### Parameters

* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/cluster/name/:name/state

### getClusterState

`GET` `/api/cluster/id/:id/state`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/cluster/id/:id/state

### getClusterResourcesByName

`GET` `/api/cluster/name/:name/resources`

#### Parameters

* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/cluster/name/:name/resources

### getClusterResources

`GET` `/api/cluster/id/:id/resources`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/cluster/id/:id/resources

### getClusterReferencesByName

`GET` `/api/cluster/name/:name/references`

#### Parameters

* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/cluster/name/:name/references

### getClusterReferences

`GET` `/api/cluster/id/:id/references`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/cluster/id/:id/references

### suppressResourcesOnCluster

`GET` `/api/cluster/id/:id/suppress-resources`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/cluster/id/:id/suppress-resources

### unsuppressResourcesOnCluster

`GET` `/api/cluster/id/:id/unsuppress-resources`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/cluster/id/:id/unsuppress-resources

<p><a id="serviceapirouter"></a></p>
## Service API Methods

Services represent things which run on Hosts.  These API calls provide information on configured Service checks and their current state.

### Get service

`GET` `/api/service/id/:id`

Get the service identified by the given UUID.

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/service/id/:id

### Execute a service check

`GET` `/api/service/id/:id/execute`

Execute a check immediately for the service identified by the given UUID.

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/service/id/:id/execute

### Suppress a service

`GET` `/api/service/id/:id/suppress`

Suppress the service identified by the given UUID, this will prevent any alerts being raised for this service.

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/service/id/:id/suppress

### Unsuppress a service

`GET` `/api/service/id/:id/unsuppress`

Unsuppress the service identified by the given UUID, this will stop preventing any alerts being raised for this service.

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/service/id/:id/unsuppress

### Get service

`GET` `/api/service/name/:host/:name`

Get the service check on the given host by name.

#### Parameters

* `host` (type: String) (provided in the URL path)
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/service/name/:host/:name

### Get service state

`GET` `/api/service/id/:id/state`

Get the state of the service identified by the given UUID.

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/service/id/:id/state

### Get service state

`GET` `/api/service/name/:host/:name/state`

Get the state of the service on the given host by name.

#### Parameters

* `host` (type: String) (provided in the URL path)
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/service/name/:host/:name/state

### Get service configuration

`GET` `/api/service/name/:host/:name/config.xml`

Get the configuration for the service on the given host by name, returning the XML configuration snippet.

#### Parameters

* `host` (type: String) (provided in the URL path)
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/service/name/:host/:name/config.xml

### Get service configuration

`GET` `/api/service/id/:id/config.xml`

Get the configuration for the service identified by the given UUID, returning the XML configuration snippet.

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/service/id/:id/config.xml

<p><a id="trapapirouter"></a></p>
## TrapAPIRouter

### getTrap

`GET` `/api/trap/id/:id`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/trap/id/:id

### suppressTrap

`GET` `/api/trap/id/:id/suppress`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/trap/id/:id/suppress

### unsuppressTrap

`GET` `/api/trap/id/:id/unsuppress`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/trap/id/:id/unsuppress

### getTrapByName

`GET` `/api/trap/name/:host/:name`

#### Parameters

* `host` (type: String) (provided in the URL path)
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/trap/name/:host/:name

### getTrapState

`GET` `/api/trap/id/:id/state`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/trap/id/:id/state

### getTrapStateByName

`GET` `/api/trap/name/:host/:name/state`

#### Parameters

* `host` (type: String) (provided in the URL path)
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/trap/name/:host/:name/state

### submitTrapStatus

`ANY` `/api/trap/id/:id/submit`

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `status` (type: String) (provided as a query parameter)
* `output` (type: String) (provided as a query parameter)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/trap/id/:id/submit?status=&output=

<p><a id="resourceapirouter"></a></p>
## ResourceAPIRouter

### suppressResource

`GET` `/api/resource/id/:id/suppress`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/resource/id/:id/suppress

### unsuppressResource

`GET` `/api/resource/id/:id/unsuppress`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/resource/id/:id/unsuppress

### getResourceState

`GET` `/api/resource/id/:id/state`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/resource/id/:id/state

### getResourceStateByName

`GET` `/api/resource/name/:host/:name/state`

#### Parameters

* `host` (type: String) (provided in the URL path)
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/resource/name/:host/:name/state

### getResourceByName

`GET` `/api/resource/name/:cluster/:name`

#### Parameters

* `cluster` (type: String) (provided in the URL path)
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/resource/name/:cluster/:name

### getResource

`GET` `/api/resource/id/:id`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/resource/id/:id

<p><a id="timeperiodapirouter"></a></p>
## TimePeriodAPIRouter

### getTimePeriod

`GET` `/api/time-period/id/:id`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/time-period/id/:id

### getTimePeriods

`GET` `/api/time-period/`

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/time-period/

### getTimePeriodByName

`GET` `/api/time-period/name/:name`

#### Parameters

* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/time-period/name/:name

<p><a id="commandapirouter"></a></p>
## CommandAPIRouter

### getCommand

`GET` `/api/command/id/:id`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/command/id/:id

### getCommands

`GET` `/api/command/`

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/command/

### getCommandByName

`GET` `/api/command/name/:name`

#### Parameters

* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/command/name/:name

<p><a id="contactapirouter"></a></p>
## ContactAPIRouter

### getContactByNameOrEmail

`GET` `/api/contact/name-or-email/:nameOrEmail`

#### Parameters

* `nameOrEmail` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/contact/name-or-email/:nameOrEmail

### getContact

`GET` `/api/contact/id/:id`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/contact/id/:id

### getContactByName

`GET` `/api/contact/name/:name`

#### Parameters

* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/contact/name/:name

### getContacts

`GET` `/api/contact/`

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/contact/

### getContactByEmail

`GET` `/api/contact/email/:email`

#### Parameters

* `email` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/contact/email/:email

<p><a id="teamapirouter"></a></p>
## TeamAPIRouter

### getTeam

`GET` `/api/team/id/:id`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/team/id/:id

### getTeams

`GET` `/api/team/`

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/team/

### getTeamByName

`GET` `/api/team/name/:name`

#### Parameters

* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/team/name/:name

### getTeamChildrenByName

`GET` `/api/team/name/:name/children`

#### Parameters

* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/team/name/:name/children

### getTeamContactsByName

`GET` `/api/team/name/:name/contacts`

#### Parameters

* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/team/name/:name/contacts

### getTeamChildren

`GET` `/api/team/id/:id/children`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/team/id/:id/children

### getTeamContacts

`GET` `/api/team/id/:id/contacts`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/team/id/:id/contacts

<p><a id="commentsapirouter"></a></p>
## CommentsAPIRouter

### getComment

`GET` `/api/comment/id/:id`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/comment/id/:id

### removeComment

`GET` `/api/comment/id/:id/remove`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/comment/id/:id/remove

### getCommentsForObject

`GET` `/api/comment/for-object/id/:id`

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `offset` (type: Long) (provided as a query parameter)
* `limit` (type: Long) (provided as a query parameter)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/comment/for-object/id/:id?offset=&limit=

### addCommentToCheck

`ANY` `/api/comment/add-comment-to-check/id/:id`

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `summary` (type: String) (provided as a query parameter)
* `comment` (type: String) (provided as a query parameter)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/comment/add-comment-to-check/id/:id?summary=&comment=

### addCommentToAlert

`ANY` `/api/comment/add-comment-to-alert/id/:id`

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `summary` (type: String) (provided as a query parameter)
* `comment` (type: String) (provided as a query parameter)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/comment/add-comment-to-alert/id/:id?summary=&comment=

### addCommentToDowntime

`ANY` `/api/comment/add-comment-to-downtime/id/:id`

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `summary` (type: String) (provided as a query parameter)
* `comment` (type: String) (provided as a query parameter)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/comment/add-comment-to-downtime/id/:id?summary=&comment=

### addCommentToObject

`ANY` `/api/comment/add-comment-to-object/id/:id`

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `summary` (type: String) (provided as a query parameter)
* `comment` (type: String) (provided as a query parameter)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/comment/add-comment-to-object/id/:id?summary=&comment=

<p><a id="downtimeapirouter"></a></p>
## DowntimeAPIRouter

### getDowntime

`GET` `/api/downtime/id/:id`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/downtime/id/:id

### removeDowntime

`GET` `/api/downtime/id/:id/remove`

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/downtime/id/:id/remove

### getDowntimeForObject

`GET` `/api/downtime/for-object/id/:id`

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `past` (type: Integer) (provided as a query parameter)
* `future` (type: Integer) (provided as a query parameter)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/downtime/for-object/id/:id?past=&future=

### addDowntimeToCheck

`ANY` `/api/downtime/add-downtime-to-check/id/:id`

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `starts` (type: Date) (provided as a query parameter)
* `ends` (type: Date) (provided as a query parameter)
* `summary` (type: String) (provided as a query parameter)
* `description` (type: String) (provided as a query parameter)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/downtime/add-downtime-to-check/id/:id?starts=&ends=&summary=&description=

<p><a id="configapirouter"></a></p>
## ConfigAPIRouter

### objectExists

`ANY` `/api/config/exists/:type/:name`

#### Parameters

* `type` (type: String) (provided in the URL path)
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/config/exists/:type/:name

### listIcons

`ANY` `/api/config/icon/`

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/config/icon/

<p><a id="statsapirouter"></a></p>
## Stats API Methods

Bergamot Monitoring tracks basic statistics about the execution of all checks, providing information on the performance of checks.

### Check Transitions

`ANY` `/api/stats/transitions/check/id/:id`

Get details of recent transitions for the check identified by the given UUID.  This will provide detail data on every execution of a check.

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `offset` (type: Long) (provided as a query parameter)
* `limit` (type: Long) (provided as a query parameter)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/stats/transitions/check/id/:id?offset=&limit=

<p><a id="utilapirouter"></a></p>
## UtilAPIRouter

### versionNumber

`GET` `/api/util/version/number`

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/util/version/number

### versionCodeName

`GET` `/api/util/version/codename`

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/util/version/codename

### newId

`GET` `/api/util/id/new`

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/util/id/new

### newIds

`GET` `/api/util/id/new/:count`

#### Parameters

* `count` (type: Integer) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/util/id/new/:count

### version

`GET` `/api/util/version`

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/util/version

<p><a id="lamplighterapirouter"></a></p>
## Lamplighter (Readings) API Methods

Lamplighter is Bergamot Monitorings internal readings (metrics) sub-system.  Lamplighter collects readings (performance metrics published by checks) and stores them for later trend analysis.

Lamplighter stores various types of metrics:

 * Gauges

 * * Int Gauge (32bit integer)

 * * Long Gauge (64bit iInteger)

 * * Float Gauge (32bit floating point

 * * Double Gauge (64bit floating point

### Latest double gauge readings

`ANY` `/api/lamplighter/graph/reading/gauge/double/:id/latest/:limit`

Get the latest readings for a double gauge.

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `limit` (type: Integer) (provided in the URL path)
* `series` (type: String) (provided as a query parameter)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/lamplighter/graph/reading/gauge/double/:id/latest/:limit?series=

### Get double gauge readings

`ANY` `/api/lamplighter/graph/reading/gauge/double/:id/date/:rollup/:agg/:start/:end`

Get double gauge readings for the given period (from start to end) applying the given aggregation method over the given rollup period.

For example we can get the 5 minute average using the `avg` aggregation method with rollup period of `300000`.

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `rollup` (type: Long) (provided in the URL path)
* `agg` (type: String) (provided in the URL path)
* `start` (type: Long) (provided in the URL path)
* `end` (type: Long) (provided in the URL path)
* `series` (type: String) (provided as a query parameter)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/lamplighter/graph/reading/gauge/double/:id/date/:rollup/:agg/:start/:end?series=

### Latest float gauge readings

`ANY` `/api/lamplighter/graph/reading/gauge/float/:id/latest/:limit`

Get the latest readings for a float gauge.

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `limit` (type: Integer) (provided in the URL path)
* `series` (type: String) (provided as a query parameter)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/lamplighter/graph/reading/gauge/float/:id/latest/:limit?series=

### Get float gauge readings

`ANY` `/api/lamplighter/graph/reading/gauge/float/:id/date/:rollup/:agg/:start/:end`

Get float gauge readings for the given period (from start to end) applying the given aggregation method over the given rollup period.

For example we can get the 5 minute average using the `avg` aggregation method with rollup period of `300000`.

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `rollup` (type: Long) (provided in the URL path)
* `agg` (type: String) (provided in the URL path)
* `start` (type: Long) (provided in the URL path)
* `end` (type: Long) (provided in the URL path)
* `series` (type: String) (provided as a query parameter)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/lamplighter/graph/reading/gauge/float/:id/date/:rollup/:agg/:start/:end?series=

### Get readings for check

`ANY` `/api/lamplighter/check/id/:id/readings`

Get the list of available readings for the check identified by the given UUID.

This will return metadata about all readings which are stored for a check, including reading ID, reading type.

#### Parameters

* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/lamplighter/check/id/:id/readings

### Latest long gauge readings

`ANY` `/api/lamplighter/graph/reading/gauge/long/:id/latest/:limit`

Get the latest readings for a long gauge.

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `limit` (type: Integer) (provided in the URL path)
* `series` (type: String) (provided as a query parameter)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/lamplighter/graph/reading/gauge/long/:id/latest/:limit?series=

### Get long gauge readings

`ANY` `/api/lamplighter/graph/reading/gauge/long/:id/date/:rollup/:agg/:start/:end`

Get long gauge readings for the given period (from start to end) applying the given aggregation method over the given rollup period.

For example we can get the 5 minute average using the `avg` aggregation method with rollup period of `300000`.

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `rollup` (type: Long) (provided in the URL path)
* `agg` (type: String) (provided in the URL path)
* `start` (type: Long) (provided in the URL path)
* `end` (type: Long) (provided in the URL path)
* `series` (type: String) (provided as a query parameter)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/lamplighter/graph/reading/gauge/long/:id/date/:rollup/:agg/:start/:end?series=

### Latest int gauge readings

`ANY` `/api/lamplighter/graph/reading/gauge/int/:id/latest/:limit`

Get the latest readings for a int gauge.

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `limit` (type: Integer) (provided in the URL path)
* `series` (type: String) (provided as a query parameter)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/lamplighter/graph/reading/gauge/int/:id/latest/:limit?series=

### Get int gauge readings

`ANY` `/api/lamplighter/graph/reading/gauge/int/:id/date/:rollup/:agg/:start/:end`

Get int gauge readings for the given period (from start to end) applying the given aggregation method over the given rollup period.

For example we can get the 5 minute average using the `avg` aggregation method with rollup period of `300000`.

#### Parameters

* `id` (type: UUID) (provided in the URL path)
* `rollup` (type: Long) (provided in the URL path)
* `agg` (type: String) (provided in the URL path)
* `start` (type: Long) (provided in the URL path)
* `end` (type: Long) (provided in the URL path)
* `series` (type: String) (provided as a query parameter)

#### Example

    curl -X GET -H 'Authorization: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/lamplighter/graph/reading/gauge/int/:id/date/:rollup/:agg/:start/:end?series=

