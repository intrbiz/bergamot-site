---
Author: Chris Ellis
Date: 2017-06-25
Code: bash
---
# Bergamot Monitoring API Reference


## AlertsAPIRouter

### GET `/api/alert/`

getAlerts

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/alert/

### GET `/api/alert/id/:id`

getAlert

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/alert/id/:id

### GET `/api/alert/for-check/id/:id`

getAlertsForCheck

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/alert/for-check/id/:id

### GET `/api/alert/current/for-check/id/:id`

getCurrentAlertForCheck

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/alert/current/for-check/id/:id

### ANY `/api/alert/id/:id/acknowledge`

acknowledgeAlert

#### Parameters
* `id` (type: UUID) (provided in the URL path)
* `summary` (type: String) (provided as a query parameter)
* `comment` (type: String) (provided as a query parameter)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/alert/id/:id/acknowledge?summary=&comment=

## HostAPIRouter

### GET `/api/host/id/:id`

getHost

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id

### GET `/api/host/name/:name`

getHostByName

#### Parameters
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/name/:name

### GET `/api/host/id/:id/execute`

executeHost

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/execute

### GET `/api/host/id/:id/execute-services`

executeServicesOnHost

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/execute-services

### GET `/api/host/id/:id/suppress-services`

suppressServicesOnHost

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/suppress-services

### GET `/api/host/id/:id/unsuppress-services`

unsuppressServicesOnHost

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/unsuppress-services

### GET `/api/host/`

getHosts

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/

### GET `/api/host/id/:id/suppress`

suppress

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/suppress

### GET `/api/host/id/:id/unsuppress`

unsuppress

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/unsuppress

### GET `/api/host/name/:name/state`

getHostStateByName

#### Parameters
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/name/:name/state

### GET `/api/host/id/:id/state`

getHostState

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/state

### GET `/api/host/name/:name/services`

getHostServicesByName

#### Parameters
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/name/:name/services

### GET `/api/host/id/:id/services`

getHostServices

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/services

### GET `/api/host/name/:name/traps`

getHostTrapsByName

#### Parameters
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/name/:name/traps

### GET `/api/host/id/:id/traps`

getHostTraps

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/traps

### GET `/api/host/id/:id/suppress-traps`

suppressTrapsOnHost

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/suppress-traps

### GET `/api/host/id/:id/unsuppress-traps`

unsuppressTrapsOnHost

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/unsuppress-traps

### GET `/api/host/id/:id/suppress-all`

suppressAllOnHost

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/suppress-all

### GET `/api/host/id/:id/unsuppress-all`

unsuppressAllOnHost

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/host/id/:id/unsuppress-all

## LocationAPIRouter

### GET `/api/location/id/:id`

getLocation

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/location/id/:id

### GET `/api/location/roots`

getRootLocations

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/location/roots

### GET `/api/location/name/:name`

getLocationByName

#### Parameters
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/location/name/:name

### GET `/api/location/id/:id/execute-all-hosts`

executeHostsInLocation

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/location/id/:id/execute-all-hosts

### GET `/api/location/`

getLocations

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/location/

### GET `/api/location/name/:name/children`

getLocationChildrenByName

#### Parameters
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/location/name/:name/children

### GET `/api/location/name/:name/hosts`

getLocationHostsByName

#### Parameters
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/location/name/:name/hosts

### GET `/api/location/id/:id/children`

getLocationChildren

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/location/id/:id/children

### GET `/api/location/id/:id/hosts`

getLocationHosts

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/location/id/:id/hosts

## GroupAPIRouter

### GET `/api/group/roots`

getRootGroups

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/group/roots

### GET `/api/group/name/:name`

getGroupByName

#### Parameters
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/group/name/:name

### GET `/api/group/id/:id`

getGroup

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/group/id/:id

### GET `/api/group/id/:id/execute-all-checks`

executeChecksInGroup

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/group/id/:id/execute-all-checks

### GET `/api/group/`

getGroups

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/group/

### GET `/api/group/name/:name/children`

getGroupChildrenByName

#### Parameters
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/group/name/:name/children

### GET `/api/group/name/:name/checks`

getGroupChecksByName

#### Parameters
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/group/name/:name/checks

### GET `/api/group/id/:id/children`

getGroupChildren

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/group/id/:id/children

### GET `/api/group/id/:id/checks`

getGroupChecks

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/group/id/:id/checks

## ClusterAPIRouter

### GET `/api/cluster/name/:name`

getClusterByName

#### Parameters
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/cluster/name/:name

### GET `/api/cluster/id/:id`

getCluster

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/cluster/id/:id

### GET `/api/cluster/id/:id/suppress`

suppressCluster

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/cluster/id/:id/suppress

### GET `/api/cluster/id/:id/unsuppress`

unsuppressCluster

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/cluster/id/:id/unsuppress

### GET `/api/cluster/`

getClusters

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/cluster/

### GET `/api/cluster/name/:name/state`

getClusterStateByName

#### Parameters
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/cluster/name/:name/state

### GET `/api/cluster/id/:id/state`

getClusterState

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/cluster/id/:id/state

### GET `/api/cluster/name/:name/resources`

getClusterResourcesByName

#### Parameters
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/cluster/name/:name/resources

### GET `/api/cluster/id/:id/resources`

getClusterResources

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/cluster/id/:id/resources

### GET `/api/cluster/name/:name/references`

getClusterReferencesByName

#### Parameters
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/cluster/name/:name/references

### GET `/api/cluster/id/:id/references`

getClusterReferences

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/cluster/id/:id/references

### GET `/api/cluster/id/:id/suppress-resources`

suppressResourcesOnCluster

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/cluster/id/:id/suppress-resources

### GET `/api/cluster/id/:id/unsuppress-resources`

unsuppressResourcesOnCluster

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/cluster/id/:id/unsuppress-resources

## ServiceAPIRouter

### GET `/api/service/id/:id`

getService

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/service/id/:id

### GET `/api/service/id/:id/execute`

executeService

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/service/id/:id/execute

### GET `/api/service/id/:id/suppress`

suppressService

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/service/id/:id/suppress

### GET `/api/service/id/:id/unsuppress`

unsuppressService

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/service/id/:id/unsuppress

### GET `/api/service/name/:host/:name`

getServiceByName

#### Parameters
* `host` (type: String) (provided in the URL path)
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/service/name/:host/:name

### GET `/api/service/id/:id/state`

getServiceState

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/service/id/:id/state

### GET `/api/service/name/:host/:name/state`

getServiceStateByName

#### Parameters
* `host` (type: String) (provided in the URL path)
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/service/name/:host/:name/state

## TrapAPIRouter

### GET `/api/trap/id/:id`

getTrap

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/trap/id/:id

### GET `/api/trap/id/:id/suppress`

suppressTrap

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/trap/id/:id/suppress

### GET `/api/trap/id/:id/unsuppress`

unsuppressTrap

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/trap/id/:id/unsuppress

### GET `/api/trap/name/:host/:name`

getTrapByName

#### Parameters
* `host` (type: String) (provided in the URL path)
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/trap/name/:host/:name

### GET `/api/trap/id/:id/state`

getTrapState

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/trap/id/:id/state

### GET `/api/trap/name/:host/:name/state`

getTrapStateByName

#### Parameters
* `host` (type: String) (provided in the URL path)
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/trap/name/:host/:name/state

### ANY `/api/trap/id/:id/submit`

submitTrapStatus

#### Parameters
* `id` (type: UUID) (provided in the URL path)
* `status` (type: String) (provided as a query parameter)
* `output` (type: String) (provided as a query parameter)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/trap/id/:id/submit?status=&output=

## ResourceAPIRouter

### GET `/api/resource/id/:id`

getResource

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/resource/id/:id

### GET `/api/resource/id/:id/suppress`

suppressResource

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/resource/id/:id/suppress

### GET `/api/resource/id/:id/unsuppress`

unsuppressResource

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/resource/id/:id/unsuppress

### GET `/api/resource/name/:cluster/:name`

getResourceByName

#### Parameters
* `cluster` (type: String) (provided in the URL path)
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/resource/name/:cluster/:name

### GET `/api/resource/id/:id/state`

getResourceState

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/resource/id/:id/state

### GET `/api/resource/name/:host/:name/state`

getResourceStateByName

#### Parameters
* `host` (type: String) (provided in the URL path)
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/resource/name/:host/:name/state

## TimePeriodAPIRouter

### GET `/api/time-period/id/:id`

getTimePeriod

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/time-period/id/:id

### GET `/api/time-period/`

getTimePeriods

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/time-period/

### GET `/api/time-period/name/:name`

getTimePeriodByName

#### Parameters
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/time-period/name/:name

## CommandAPIRouter

### GET `/api/command/id/:id`

getCommand

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/command/id/:id

### GET `/api/command/`

getCommands

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/command/

### GET `/api/command/name/:name`

getCommandByName

#### Parameters
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/command/name/:name

## ContactAPIRouter

### GET `/api/contact/name-or-email/:nameOrEmail`

getContactByNameOrEmail

#### Parameters
* `nameOrEmail` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/contact/name-or-email/:nameOrEmail

### GET `/api/contact/id/:id`

getContact

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/contact/id/:id

### GET `/api/contact/name/:name`

getContactByName

#### Parameters
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/contact/name/:name

### GET `/api/contact/`

getContacts

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/contact/

### GET `/api/contact/email/:email`

getContactByEmail

#### Parameters
* `email` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/contact/email/:email

## TeamAPIRouter

### GET `/api/team/id/:id`

getTeam

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/team/id/:id

### GET `/api/team/`

getTeams

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/team/

### GET `/api/team/name/:name`

getTeamByName

#### Parameters
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/team/name/:name

### GET `/api/team/name/:name/children`

getTeamChildrenByName

#### Parameters
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/team/name/:name/children

### GET `/api/team/name/:name/contacts`

getTeamContactsByName

#### Parameters
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/team/name/:name/contacts

### GET `/api/team/id/:id/children`

getTeamChildren

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/team/id/:id/children

### GET `/api/team/id/:id/contacts`

getTeamContacts

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/team/id/:id/contacts

## CommentsAPIRouter

### GET `/api/comment/id/:id`

getComment

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/comment/id/:id

### GET `/api/comment/id/:id/remove`

removeComment

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/comment/id/:id/remove

### GET `/api/comment/for-object/id/:id`

getCommentsForObject

#### Parameters
* `id` (type: UUID) (provided in the URL path)
* `offset` (type: Long) (provided as a query parameter)
* `limit` (type: Long) (provided as a query parameter)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/comment/for-object/id/:id?offset=&limit=

### ANY `/api/comment/add-comment-to-check/id/:id`

addCommentToCheck

#### Parameters
* `id` (type: UUID) (provided in the URL path)
* `summary` (type: String) (provided as a query parameter)
* `comment` (type: String) (provided as a query parameter)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/comment/add-comment-to-check/id/:id?summary=&comment=

### ANY `/api/comment/add-comment-to-alert/id/:id`

addCommentToAlert

#### Parameters
* `id` (type: UUID) (provided in the URL path)
* `summary` (type: String) (provided as a query parameter)
* `comment` (type: String) (provided as a query parameter)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/comment/add-comment-to-alert/id/:id?summary=&comment=

### ANY `/api/comment/add-comment-to-downtime/id/:id`

addCommentToDowntime

#### Parameters
* `id` (type: UUID) (provided in the URL path)
* `summary` (type: String) (provided as a query parameter)
* `comment` (type: String) (provided as a query parameter)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/comment/add-comment-to-downtime/id/:id?summary=&comment=

### ANY `/api/comment/add-comment-to-object/id/:id`

addCommentToObject

#### Parameters
* `id` (type: UUID) (provided in the URL path)
* `summary` (type: String) (provided as a query parameter)
* `comment` (type: String) (provided as a query parameter)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/comment/add-comment-to-object/id/:id?summary=&comment=

## DowntimeAPIRouter

### GET `/api/downtime/for-object/id/:id`

getDowntimeForObject

#### Parameters
* `id` (type: UUID) (provided in the URL path)
* `past` (type: Integer) (provided as a query parameter)
* `future` (type: Integer) (provided as a query parameter)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/downtime/for-object/id/:id?past=&future=

### GET `/api/downtime/id/:id/remove`

removeDowntime

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/downtime/id/:id/remove

### ANY `/api/downtime/add-downtime-to-check/id/:id`

addDowntimeToCheck

#### Parameters
* `id` (type: UUID) (provided in the URL path)
* `starts` (type: Date) (provided as a query parameter)
* `ends` (type: Date) (provided as a query parameter)
* `summary` (type: String) (provided as a query parameter)
* `description` (type: String) (provided as a query parameter)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/downtime/add-downtime-to-check/id/:id?starts=&ends=&summary=&description=

### GET `/api/downtime/id/:id`

getDowntime

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/downtime/id/:id

## ConfigAPIRouter

### ANY `/api/config/exists/:type/:name`

objectExists

#### Parameters
* `type` (type: String) (provided in the URL path)
* `name` (type: String) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/config/exists/:type/:name

### ANY `/api/config/icon/`

listIcons

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/config/icon/

## StatsAPIRouter

### ANY `/api/stats/transitions/check/id/:id`

getCheckTransitions

#### Parameters
* `id` (type: UUID) (provided in the URL path)
* `offset` (type: Long) (provided as a query parameter)
* `limit` (type: Long) (provided as a query parameter)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/stats/transitions/check/id/:id?offset=&limit=

## UtilAPIRouter

### GET `/api/util/version`

version

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/util/version

### GET `/api/util/version/number`

versionNumber

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/util/version/number

### GET `/api/util/version/codename`

versionCodeName

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/util/version/codename

### GET `/api/util/id/new`

newId

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/util/id/new

### GET `/api/util/id/new/:count`

newIds

#### Parameters
* `count` (type: Integer) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/util/id/new/:count

## LamplighterAPIRouter

### ANY `/api/lamplighter/check/id/:id/readings`

getReadingsByCheck

#### Parameters
* `id` (type: UUID) (provided in the URL path)

#### Example

    curl -X GET -H 'X-Bergamot-Auth: WVArodeKagxYAK0f4w61BB4XCOWcpTINgu2DQVpx4FwIFKNgCF6bEwtXhAxdeH9uT5029bXWsiA8bHv7wgR7ZGe0S-rddU3tMMUQJTFf' https://demo.bergamot-monitoring.org/api/lamplighter/check/id/:id/readings

