import http from "../http-common";
const jsonAggregate = require('json-aggregate')

class StatsDataService {

  async test() {
      const visits = await http.get(`/visits`)
      .catch(err => { console.log(err); });
      const json = JSON.stringify(visits.data);
      const aggregate = jsonAggregate.create(json).match({ clientId: 1234 }).exec();
      console.log('aggregated to: ');
      console.log(aggregate);
      return aggregate;
  }

  async getReferralsStats() {
      var visitsData = await http.get(`/visits`)
      .catch(err => { console.log(err); });

      var referralsData = await http.get(`/referrals`)
      .catch(err => { console.log(err); });

      // get clientIds with zones
      /*
      var zoneMap = {};
      visitsData.data.forEach(function(item, index) {
          zoneMap[item.clientId] = item.zoneLocation.split(" ")[0];
      });

      // get disability counts
      const disabilityData = await http.get(`/disability`)
      .catch(err => { console.log(err); });

      disabilityData.data.forEach(function(item, index) {
          item.zone = zoneMap[item.clientId];
      });


      const disabilityJson = JSON.stringify(disabilityData.data);
      const aggregate = jsonAggregate.create(disabilityJson);

      */
      var json = JSON.stringify(visitsData.data);
      var aggregate = jsonAggregate.create(json);

      console.log('Referrals Data');
      console.log(aggregate);

      var referralsCount = aggregate.match({ isDCRChecked: true }).exec().length;
      const referralsCounts = {
      0: { id: 'referralsCount', count: referralsCount },
      };

      var json = JSON.stringify(referralsData.data);
      var aggregate = jsonAggregate.create(json);

      function getReferralsForAll() {
          var requirePhysiotherapyCount = aggregate.match({ requirePhysiotherapy: true }).exec().length;
          var requireProstheticCount = aggregate.match({ requireProsthetic: true }).exec().length;
          var requireOrthotic = aggregate.match({ requireOrthotic: true }).exec().length;
          var requireWheelchair = aggregate.match({ requireWheelchair: true }).exec().length;
          var requireOther = aggregate.match({ requireOther: true }).exec().length;
          var amputeeDisability = aggregate.match({ amputeeDisability: true }).exec().length;
          var polioDisability = aggregate.match({ polioDisability: true }).exec().length;
          var spinalCordInjuryDisability = aggregate.match({ spinalCordInjuryDisability: true }).exec().length;
          var cerebralPalsyDisability = aggregate.match({ cerebralPalsyDisability: true }).exec().length;
          var spinaBifidaDisability = aggregate.match({ spinaBifidaDisability: true }).exec().length;
          var hydrocephalusDisability = aggregate.match({ hydrocephalusDisability: true }).exec().length;
          var visualImpairmentDisability = aggregate.match({ visualImpairmentDisability: true }).exec().length;
          var otherDisability = aggregate.match({ otherDisability: true }).exec().length;
          var isInjuryAboveKnee = aggregate.match({ isInjuryAboveKnee: true }).exec().length;
          var isInjuryBelowKnee = aggregate.match({ isInjuryBelowKnee: true }).exec().length;
          var isIntermediateWheelchairUser = aggregate.match({ isIntermediateWheelchairUser: true }).exec().length;
          var hipWidth = aggregate.match({ hipWidth: true }).exec().length;
          var hasExistingWheelchair = aggregate.match({ hasExistingWheelchair: true }).exec().length;
          var canRepairWheelchair = aggregate.match({ canRepairWheelchair: true }).exec().length;

          const referralCountsByType = {
          0: { id: 'Requires Physiotherapy', count: requirePhysiotherapyCount },
          1: { id: 'Requires Prosthetic', count: requireProstheticCount },
          2: { id: 'Requires Orthotic', count: requireOrthotic },
          3: { id: 'Require Wheelchair', count: requireWheelchair },
          4: { id: 'Requires Other', count: requireOther },
          5: { id: 'Amputee Disability', count: amputeeDisability },
          6: { id: 'Polio Disability', count: polioDisability },
          7: { id: 'Spinal Cord Injury Disability', count: spinalCordInjuryDisability },
          8: { id: 'cerebral Palsy Disability', count: cerebralPalsyDisability },
          9: { id: 'spinaBifida Disability', count: spinaBifidaDisability },
          10: { id: 'hydrocephalus Disability', count: hydrocephalusDisability },
          11: { id: 'Visual Impairment Disability', count: visualImpairmentDisability },
          12: { id: 'Other Disability', count: otherDisability },
          13: { id: 'Has Injury Above Knee', count: isInjuryAboveKnee },
          14: { id: 'Has Injury Below Knee', count: isInjuryBelowKnee },
          15: { id: 'Has Intermediate Wheelchair experience', count: isIntermediateWheelchairUser },
          16: { id: 'Hip Width', count: hipWidth },
          17: { id: 'has Existing Wheelchair', count: hasExistingWheelchair },
          18: { id: 'Can Repair Wheelchair', count: canRepairWheelchair },
          };

          return referralCountsByType;
      }




      /*

      // get counts for each zones
      function getDisabilityForBidibidi() {
          var amputeeDisabilityCount = aggregate.match({ zone: 'Bidibidi', amputeeDisability: true }).exec().length;
          var polioDisabilityCount = aggregate.match({ zone: 'Bidibidi', polioDisability: true }).exec().length;
          var spinalCordInjuryDisabilityCount = aggregate.match({ zone: 'Bidibidi', spinalCordInjuryDisability: true }).exec().length;
          var cerebralPalsyDisabilityCount = aggregate.match({ zone: 'Bidibidi', cerebralPalsyDisability: true }).exec().length;
          var spinaBifidaDisabilityCount = aggregate.match({ zone: 'Bidibidi', spinaBifidaDisability: true }).exec().length;
          var hydrocephalusDisabilityCount = aggregate.match({ zone: 'Bidibidi', hydrocephalusDisability: true }).exec().length;
          var visualImpairmentDisabilityCount = aggregate.match({ zone: 'Bidibidi', visualImpairmentDisability: true }).exec().length;
          var hearingImpairmentDisabilityCount = aggregate.match({ zone: 'Bidibidi', hearingImpairmentDisability: true }).exec().length;
          var doNotKnowDisabilityCount = aggregate.match({ zone: 'Bidibidi', doNotKnowDisability: true }).exec().length;
          var otherDisabilityCount = aggregate.match({ zone: 'Bidibidi', otherDisability: true }).exec().length;

          const disabilityCounts = {
          amputeeDisability: amputeeDisabilityCount,
          polioDisability: polioDisabilityCount,
          spinalCordInjuryDisability: spinalCordInjuryDisabilityCount,
          cerebralPalsyDisability: cerebralPalsyDisabilityCount,
          spinaBifidaDisability: spinaBifidaDisabilityCount,
          hydrocephalusDisability: hydrocephalusDisabilityCount,
          visualImpairmentDisability: visualImpairmentDisabilityCount,
          hearingImpairmentDisability: hearingImpairmentDisabilityCount,
          doNotKnowDisability: doNotKnowDisabilityCount,
          otherDisability: otherDisabilityCount
          };

          return disabilityCounts;
      }
      var allDisabilityCounts = getDisabilityForAll();
      var bidibidiDisabilityCounts = getDisabilityForBidibidi();
      var palorinyaDisabilityCounts = getDisabilityForPalorinya();
      */
      var allReferralsByType = getReferralsForAll();
      var allReferralsSum = referralsCounts;

      /*
      const statsData = {allDisabilityCounts: allDisabilityCounts,
                         bidibidiDisabilityCounts: bidibidiDisabilityCounts,
                         palorinyaDisabilityCounts: palorinyaDisabilityCounts};
      */
      const statsData = {allReferralsSum: allReferralsSum,
                         allReferralsByType: allReferralsByType};


      console.log('Aggregated to:');
      console.log(statsData);


      return statsData;

  }


  async getDisabilityStats() {
      var zoneData = await http.get(`/clients`)
      .catch(err => { console.log(err); });

      // get clientIds with zones
      var zoneMap = {};
      zoneData.data.forEach(function(item, index) {
          zoneMap[item.clientId] = item.zoneLocation.split(" ")[0];
      });

      // get disability counts
      const disabilityData = await http.get(`/disability`)
      .catch(err => { console.log(err); });

      disabilityData.data.forEach(function(item, index) {
          item.zone = zoneMap[item.clientId];
      });


      const disabilityJson = JSON.stringify(disabilityData.data);
      const aggregate = jsonAggregate.create(disabilityJson);


      // get counts for each zones
      function getDisabilityForBidibidi() {
          var amputeeDisabilityCount = aggregate.match({ zone: 'Bidibidi', amputeeDisability: true }).exec().length;
          var polioDisabilityCount = aggregate.match({ zone: 'Bidibidi', polioDisability: true }).exec().length;
          var spinalCordInjuryDisabilityCount = aggregate.match({ zone: 'Bidibidi', spinalCordInjuryDisability: true }).exec().length;
          var cerebralPalsyDisabilityCount = aggregate.match({ zone: 'Bidibidi', cerebralPalsyDisability: true }).exec().length;
          var spinaBifidaDisabilityCount = aggregate.match({ zone: 'Bidibidi', spinaBifidaDisability: true }).exec().length;
          var hydrocephalusDisabilityCount = aggregate.match({ zone: 'Bidibidi', hydrocephalusDisability: true }).exec().length;
          var visualImpairmentDisabilityCount = aggregate.match({ zone: 'Bidibidi', visualImpairmentDisability: true }).exec().length;
          var hearingImpairmentDisabilityCount = aggregate.match({ zone: 'Bidibidi', hearingImpairmentDisability: true }).exec().length;
          var doNotKnowDisabilityCount = aggregate.match({ zone: 'Bidibidi', doNotKnowDisability: true }).exec().length;
          var otherDisabilityCount = aggregate.match({ zone: 'Bidibidi', otherDisability: true }).exec().length;

          const disabilityCounts = {
          0: { id: 'amputeeDisability', count: amputeeDisabilityCount },
          1: { id: 'polioDisability', count: polioDisabilityCount },
          2: { id: 'spinalCordInjuryDisability', count: spinalCordInjuryDisabilityCount },
          3: { id: 'cerebralPalsyDisability', count: cerebralPalsyDisabilityCount },
          4: { id: 'spinaBifidaDisability', count: spinaBifidaDisabilityCount },
          5: { id: 'hydrocephalusDisability', count: hydrocephalusDisabilityCount },
          6: { id: 'visualImpairmentDisability', count: visualImpairmentDisabilityCount },
          7: { id: 'hearingImpairmentDisability', count: hearingImpairmentDisabilityCount },
          9: { id: 'doNotKnowDisability', count: doNotKnowDisabilityCount },
          0: { id: 'otherDisability', count: otherDisabilityCount }
          };

          return disabilityCounts;
      }

      function getDisabilityForPalorinya() {
          var amputeeDisabilityCount = aggregate.match({ zone: 'Palorinya', amputeeDisability: true }).exec().length;
          var polioDisabilityCount = aggregate.match({ zone: 'Palorinya', polioDisability: true }).exec().length;
          var spinalCordInjuryDisabilityCount = aggregate.match({ zone: 'Palorinya', spinalCordInjuryDisability: true }).exec().length;
          var cerebralPalsyDisabilityCount = aggregate.match({ zone: 'Palorinya', cerebralPalsyDisability: true }).exec().length;
          var spinaBifidaDisabilityCount = aggregate.match({ zone: 'Palorinya', spinaBifidaDisability: true }).exec().length;
          var hydrocephalusDisabilityCount = aggregate.match({ zone: 'Palorinya', hydrocephalusDisability: true }).exec().length;
          var visualImpairmentDisabilityCount = aggregate.match({ zone: 'Palorinya', visualImpairmentDisability: true }).exec().length;
          var hearingImpairmentDisabilityCount = aggregate.match({ zone: 'Palorinya', hearingImpairmentDisability: true }).exec().length;
          var doNotKnowDisabilityCount = aggregate.match({ zone: 'Palorinya', doNotKnowDisability: true }).exec().length;
          var otherDisabilityCount = aggregate.match({ zone: 'Palorinya', otherDisability: true }).exec().length;

          const disabilityCounts = {
          0: { id: 'amputeeDisability', count: amputeeDisabilityCount },
          1: { id: 'polioDisability', count: polioDisabilityCount },
          2: { id: 'spinalCordInjuryDisability', count: spinalCordInjuryDisabilityCount },
          3: { id: 'cerebralPalsyDisability', count: cerebralPalsyDisabilityCount },
          4: { id: 'spinaBifidaDisability', count: spinaBifidaDisabilityCount },
          5: { id: 'hydrocephalusDisability', count: hydrocephalusDisabilityCount },
          6: { id: 'visualImpairmentDisability', count: visualImpairmentDisabilityCount },
          7: { id: 'hearingImpairmentDisability', count: hearingImpairmentDisabilityCount },
          9: { id: 'doNotKnowDisability', count: doNotKnowDisabilityCount },
          0: { id: 'otherDisability', count: otherDisabilityCount }
          };

          return disabilityCounts;
      }



      // get counts for all zones
      function getDisabilityForAll() {
          var amputeeDisabilityCount = aggregate.match({ amputeeDisability: true }).exec().length;
          var polioDisabilityCount = aggregate.match({ polioDisability: true }).exec().length;
          var spinalCordInjuryDisabilityCount = aggregate.match({ spinalCordInjuryDisability: true }).exec().length;
          var cerebralPalsyDisabilityCount = aggregate.match({ cerebralPalsyDisability: true }).exec().length;
          var spinaBifidaDisabilityCount = aggregate.match({ spinaBifidaDisability: true }).exec().length;
          var hydrocephalusDisabilityCount = aggregate.match({ hydrocephalusDisability: true }).exec().length;
          var visualImpairmentDisabilityCount = aggregate.match({ visualImpairmentDisability: true }).exec().length;
          var hearingImpairmentDisabilityCount = aggregate.match({ hearingImpairmentDisability: true }).exec().length;
          var doNotKnowDisabilityCount = aggregate.match({ doNotKnowDisability: true }).exec().length;
          var otherDisabilityCount = aggregate.match({ otherDisability: true }).exec().length;

          const disabilityCounts = {
          0: { id: 'amputeeDisability', count: amputeeDisabilityCount },
          1: { id: 'polioDisability', count: polioDisabilityCount },
          2: { id: 'spinalCordInjuryDisability', count: spinalCordInjuryDisabilityCount },
          3: { id: 'cerebralPalsyDisability', count: cerebralPalsyDisabilityCount },
          4: { id: 'spinaBifidaDisability', count: spinaBifidaDisabilityCount },
          5: { id: 'hydrocephalusDisability', count: hydrocephalusDisabilityCount },
          6: { id: 'visualImpairmentDisability', count: visualImpairmentDisabilityCount },
          7: { id: 'hearingImpairmentDisability', count: hearingImpairmentDisabilityCount },
          9: { id: 'doNotKnowDisability', count: doNotKnowDisabilityCount },
          0: { id: 'otherDisability', count: otherDisabilityCount }
          };

          return disabilityCounts;
      }

      var allDisabilityCounts = getDisabilityForAll();
      var bidibidiDisabilityCounts = getDisabilityForBidibidi();
      var palorinyaDisabilityCounts = getDisabilityForPalorinya();

      const zoneDisabilityCounts = {};
      const statsData = {allDisabilityCounts: allDisabilityCounts,
                         bidibidiDisabilityCounts: bidibidiDisabilityCounts,
                         palorinyaDisabilityCounts: palorinyaDisabilityCounts};



      return statsData;

  }

  async getNumberOfVisitsPerCBRWorker() {
    const requestVisits = await http.get(`/visits`).catch(err => {
      console.log(err);
    })
    const requestUsers = await http.get(`/users`).catch(err => {
      console.log(err);
    })

    const usersData = jsonAggregate.create(JSON.stringify(requestUsers.data));
    const visitsData = jsonAggregate.create(JSON.stringify(requestVisits.data));

    var visitsPerCBRWorker = visitsData.group({
      id: "workerName",
      count: { $sum: 1 }
    }).exec();

    usersData.data.forEach(users => {
      var isUserInVisits = false;
      var usersWorkerName = users.firstName + " " + users.lastName;
      visitsPerCBRWorker.forEach(visits => {
        if (usersWorkerName === visits.id) {
          isUserInVisits = true;
        }
      });
      if (!isUserInVisits) {
        visitsPerCBRWorker.push({id: usersWorkerName, count: 0});
      }
    });


    return visitsPerCBRWorker;
  }

  async getRisks() {

      function calcPercentage(obj) {
        // get sum
        var sum = 0;
        var i, j;
        for (i in obj) {
          for (j in obj[i]) {
            sum += obj[i][j].count;
          }
        }
        // calc percentage
        for (i in obj) {
          for (j in obj[i]) {
            obj[i][j].percentage = parseFloat((obj[i][j].count / sum).toFixed(3));
          }
        }
        return obj;
      }

      function removeDoubleNestArry(a_obj) {
        var i, j;
        var newArray = [];
        for (i in a_obj) {
          newArray.push(a_obj[i][0]);
        }
        return newArray;
      }

      function setUndefinedCountToZero(obj) {
        var boolArray = [false, false, false, false];
        if (obj[0].length === 0) {
          boolArray[0] = true;
        }
        if (obj[1].length === 0) {
          boolArray[1] = true;
        }
        if (obj[2].length === 0) {
          boolArray[2] = true;
        }
        if (obj[3].length === 0) {
          boolArray[3] = true;
        }

        if (boolArray[0] == true) {
          obj[0] = [{id: "Critical risk", count: 0}];
        }
        if (boolArray[1] == true) {
          obj[1] = [{id: "Medium risk", count: 0}];
        }
        if (boolArray[2] == true) {
          obj[2] = [{id: "High risk", count: 0}];
        }
        if (boolArray[3] == true) {
          obj[3] = [{id: "Low risk", count: 0}];
        }


        return obj;
      }

      async function getHealthAspectRiskStats() {
          const rateAspect = 'healthAspect';
          const res = await http.get(rateAspect)
                                .catch(err => { console.log(err); });
          const data = jsonAggregate.create(JSON.stringify(res.data));

          var criticalCount = data
                                      .match({ rateHealth: 'Critical risk' })
                                      .group({ id: 'rateHealth',
                                               count: { $sum: 1 } })
                                      .exec();

          var highCount = data
                                      .match({ rateHealth: 'High risk' })
                                      .group({ id: 'rateHealth',
                                               count: { $sum: 1 } })
                                      .exec();

          var mediumCount = data
                                      .match({ rateHealth: 'Medium risk' })
                                      .group({ id: 'rateHealth',
                                               count: { $sum: 1 } })
                                      .exec();


          var lowCount = data
                                      .match({ rateHealth: 'Low risk' })
                                      .group({ id: 'rateHealth',
                                               count: { $sum: 1 } })
                                      .exec();


          // Format Data
          var riskStats = [criticalCount, mediumCount, highCount, lowCount];
          var riskStats = setUndefinedCountToZero(riskStats);
          var stats_percentage = calcPercentage(riskStats);
          var statsData = removeDoubleNestArry(stats_percentage);

          return statsData;
      }

      async function getEducationAspectRiskStats() {
          const rateAspect = 'educationAspect';
          const res = await http.get(rateAspect)
                                .catch(err => { console.log(err); });
          const data = jsonAggregate.create(JSON.stringify(res.data));

          var criticalCount = data
                                      .match({ rateEducation: 'Critical risk' })
                                      .group({ id: 'rateEducation',
                                               count: { $sum: 1 } })
                                      .exec();

          var highCount = data
                                      .match({ rateEducation: 'High risk' })
                                      .group({ id: 'rateEducation',
                                               count: { $sum: 1 } })
                                      .exec();

          var mediumCount = data
                                      .match({ rateEducation: 'Medium risk' })
                                      .group({ id: 'rateEducation',
                                               count: { $sum: 1 } })
                                      .exec();


          var lowCount = data
                                      .match({ rateEducation: 'Low risk' })
                                      .group({ id: 'rateEducation',
                                               count: { $sum: 1 } })
                                      .exec();

          // Format Data
          var riskStats = [criticalCount, mediumCount, highCount, lowCount];
          var filteredRiskStats = setUndefinedCountToZero(riskStats);
          var stats_percentage = calcPercentage(filteredRiskStats);
          var statsData = removeDoubleNestArry(stats_percentage);

          return statsData;
      }

      async function getSocialAspectRiskStats() {
          const rateAspect = 'socialAspect';
          const res = await http.get(rateAspect)
                                .catch(err => { console.log(err); });
          const data = jsonAggregate.create(JSON.stringify(res.data));

          var criticalCount = data
                                      .match({ rateSocialStatus: 'Critical risk' })
                                      .group({ id: 'rateSocialStatus',
                                               count: { $sum: 1 } })
                                      .exec();

          var highCount = data
                                      .match({ rateSocialStatus: 'High risk' })
                                      .group({ id: 'rateSocialStatus',
                                               count: { $sum: 1 } })
                                      .exec();

          var mediumCount = data
                                      .match({ rateSocialStatus: 'Medium risk' })
                                      .group({ id: 'rateSocialStatus',
                                               count: { $sum: 1 } })
                                      .exec();


          var lowCount = data
                                      .match({ rateSocialStatus: 'Low risk' })
                                      .group({ id: 'rateSocialStatus',
                                               count: { $sum: 1 } })
                                      .exec();

          // Format Data
          var riskStats = [criticalCount, mediumCount, highCount, lowCount];
          var filteredRiskStats = setUndefinedCountToZero(riskStats);
          var stats_percentage = calcPercentage(filteredRiskStats);
          var statsData = removeDoubleNestArry(stats_percentage);

          return statsData;
      }

      function getAllAspectRiskStats(riskData) {
          const data = jsonAggregate.create(JSON.stringify(riskData));

          var allRiskCounts = data
                                      .group({ id: 'id',
                                               count: { $sum: 'count' } })
                                      .exec();

          // Format Data
          var stats_percentage = calcPercentage([allRiskCounts]);

          return stats_percentage[0];
      }




      // get individual risk data
      const healthRiskData = await getHealthAspectRiskStats();
      const educationRiskData = await getEducationAspectRiskStats();
      const socialRiskData = await getSocialAspectRiskStats();

      var statsObj = {'healthRisk': healthRiskData,
                        'educationRisk': educationRiskData,
                        'socialRisk': socialRiskData
                       };

      // get all risk data
      const allRiskDataRaw = healthRiskData.concat(educationRiskData).concat(socialRiskData);
      const allRiskData = getAllAspectRiskStats(allRiskDataRaw);
      statsObj.allRisk = allRiskData;

      //console.log('aggregated to: ');
      //console.log(statsObj);


      return statsObj;
  }

}

export default new StatsDataService();
